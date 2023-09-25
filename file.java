provider "aws" {
  region = "us-east-1" # Set your desired AWS region
}

resource "aws_s3_bucket" "buckets" {
  for_each = var.buckets

  bucket = each.key
  acl    = each.value.acl
  region = each.value.region

  versioning {
    enabled = each.value.versioning_enabled
  }

  dynamic "logging" {
    for_each = can(lookup(each.value, "logging_target_bucket")) ? [1] : []
    content {
      target_bucket = each.value.logging_target_bucket
      target_prefix = "logs/"
    }
  }

  dynamic "cors_rule" {
    for_each = length(each.value.cors_rules) > 0 ? [1] : []
    content {
      allowed_headers = each.value.cors_rules[0].allowed_headers
      allowed_methods = each.value.cors_rules[0].allowed_methods
      allowed_origins = each.value.cors_rules[0].allowed_origins
      expose_headers  = each.value.cors_rules[0].expose_headers
      max_age_seconds = each.value.cors_rules[0].max_age_seconds
    }
  }

  tags = each.value.tags
}
