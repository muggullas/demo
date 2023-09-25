variable "buckets" {
  type = map(object({
    acl    = string
    region = string
    tags   = map(string)
    versioning_enabled = bool
    logging_target_bucket = string
    cors_rules = list(object({
      allowed_headers = list(string)
      allowed_methods = list(string)
      allowed_origins = list(string)
      expose_headers  = list(string)
      max_age_seconds = number
    }))
  }))
  default = {
    example_bucket_1 = {
      acl    = "private"
      region = "us-east-1"
      tags   = {
        Name = "ExampleBucket1"
      }
      versioning_enabled = true
      logging_target_bucket = "example-logs-bucket"
      cors_rules = [
        {
          allowed_headers = ["Authorization"]
          allowed_methods = ["GET", "PUT", "POST"]
          allowed_origins = ["https://example.com"]
          expose_headers  = ["ETag"]
          max_age_seconds = 3600
        },
      ]
    }
    example_bucket_2 = {
      acl    = "public-read"
      region = "us-west-2"
      tags   = {
        Name = "ExampleBucket2"
      }
      versioning_enabled = false
    }
    # Add more bucket configurations as needed
  }
}
