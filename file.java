provider "aws" {
  region = "us-east-1" # Set your desired AWS region
}

# Define a map of S3 bucket configurations
variable "buckets" {
  type = map(object({
    acl    = string # ACL for the bucket (e.g., "private", "public-read")
    region = string # Region for the bucket
    tags   = map(string) # Tags for the bucket
  }))
  default = {
    example_bucket_1 = {
      acl    = "private"
      region = "us-east-1"
      tags   = {
        Name = "ExampleBucket1"
      }
    }
    example_bucket_2 = {
      acl    = "public-read"
      region = "us-west-2"
      tags   = {
        Name = "ExampleBucket2"
      }
    }
    # Add more bucket configurations as needed
  }
}

resource "aws_s3_bucket" "buckets" {
  for_each = var.buckets

  bucket = each.key
  acl    = each.value.acl
  region = each.value.region

  tags = each.value.tags
}
