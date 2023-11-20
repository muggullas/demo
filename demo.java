based on the below scripts add this tags into this script:

tags:

Key
Value
ManagedBy	environment-operator
Name	dattdl-prod-nifi-processed-migrated

main.tf

##datdl-prod-glue-deltalake###
resource "aws_s3_bucket" "glue_deltalake" {
  bucket = "datdl-prod-glue-deltalake"
  acl    = "private"

  versioning {
    enabled = false
  }

  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm     = "aws:kms"
        kms_master_key_id = "arn:aws:kms:eu-central-1:410914755127:key/mrk-4074aa1ee14d4fe5bf4119cd74bde35c"
      }
    }
  }
}
