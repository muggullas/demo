so again the state file is update like below still getting the same same error how to fix the error please review the files and provide the fixer
##error:

Error: creating Amazon S3 (Simple Storage) Bucket (sagemaker-eu-central-1-689491089140): BucketAlreadyOwnedByYou: Your previous request to create the named bucket succeeded and you already own it.
        status code: 409, request id: JEAMQ53BQVS4GNSC, host id: ZABCcRI8iwfoYP5+iQfGZ+SpnkbEQ4Q5SlUi7RVKHwhObE+HOxni5gHFptCeG3BCcyUL/76b10U=

  on modules/s3/main.tf line 18, in resource "aws_s3_bucket" "sagemaker_bucket":
  18: resource "aws_s3_bucket" "sagemaker_bucket" {

##command:  terraform import module.aws_s3.aws_s3_bucket.sagemaker_bucket sagemaker-eu-central-1-689491089140

##main.tf


resource "aws_s3_bucket" "sagemaker_bucket" {
  bucket = "sagemaker-eu-central-1-689491089140" # The name of your S3 bucket
  acl    = "private" # Replace with your desired access control settings
  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm     = "aws:kms"
        kms_master_key_id = "arn:aws:kms:eu-central-1:689491089140:key/1860c4f0-5d5b-4d76-b7fc-eabfaf557a00"
      }
    }
  }
}

data "aws_s3_bucket" "sagemaker_bucket" {
  bucket = "sagemaker-eu-central-1-689491089140" # The name of your S3 bucket
}

##statefile:

      {
  "version": 4,
  "terraform_version": "0.13.7",
  "serial": 39,
  "lineage": "3f9c9cce-f53d-c69a-cf49-481c35f4eef0",
  "outputs": {},
  "resources": [
    {
      "mode": "data",
      "type": "aws_caller_identity",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "account_id": "689491089140",
            "arn": "arn:aws:sts::689491089140:assumed-role/dat_gitlab_cicd_authentication/AssumeRoleSession1",
            "id": "2023-08-23 14:22:01.8854606 +0000 UTC",
            "user_id": "AROA2BCG7C32FRGKERJ72:AssumeRoleSession1"
          }
        }
      ]
    },
    {
      "module": "module.aws_backend",
      "mode": "managed",
      "type": "aws_dynamodb_table",
      "name": "terraform_state_locking_dynamodb",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 1,
          "attributes": {
            "arn": "arn:aws:dynamodb:eu-central-1:689491089140:table/datdl-infra-dynamodbterraform-backend-dev-euc1",
            "attribute": [
              {
                "name": "LockID",
                "type": "S"
              }
            ],
            "billing_mode": "PROVISIONED",
            "global_secondary_index": [],
            "hash_key": "LockID",
            "id": "datdl-infra-dynamodbterraform-backend-dev-euc1",
            "local_secondary_index": [],
            "name": "datdl-infra-dynamodbterraform-backend-dev-euc1",
            "point_in_time_recovery": [
              {
                "enabled": false
              }
            ],
            "range_key": null,
            "read_capacity": 5,
            "replica": [],
            "server_side_encryption": [
              {
                "enabled": true,
                "kms_key_arn": "arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4"
              }
            ],
            "stream_arn": "",
            "stream_enabled": false,
            "stream_label": "",
            "stream_view_type": "",
            "tags": {},
            "timeouts": null,
            "ttl": [
              {
                "attribute_name": "",
                "enabled": false
              }
            ],
            "write_capacity": 5
          },
          "private": "eyJlMmJmYjczMC1lY2FhLTExZTYtOGY4OC0zNDM2M2JjN2M0YzAiOnsiY3JlYXRlIjoxODAwMDAwMDAwMDAwLCJkZWxldGUiOjYwMDAwMDAwMDAwMCwidXBkYXRlIjozNjAwMDAwMDAwMDAwfSwic2NoZW1hX3ZlcnNpb24iOiIxIn0="
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "aws_caller_identity",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "account_id": "689491089140",
            "arn": "arn:aws:sts::689491089140:assumed-role/dat_gitlab_cicd_authentication/AssumeRoleSession1",
            "id": "2023-08-23 14:22:01.8342954 +0000 UTC",
            "user_id": "AROA2BCG7C32FRGKERJ72:AssumeRoleSession1"
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "aws_iam_policy_document",
      "name": "lambda-assume-role-policy",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "id": "601522199",
            "json": "{\n  \"Version\": \"2012-10-17\",\n  \"Statement\": [\n    {\n      \"Sid\": \"\",\n      \"Effect\": \"Allow\",\n      \"Action\": \"sts:AssumeRole\",\n      \"Principal\": {\n        \"Service\": \"lambda.amazonaws.com\"\n      }\n    }\n  ]\n}",
            "override_json": null,
            "policy_id": null,
            "source_json": null,
            "statement": [
              {
                "actions": [
                  "sts:AssumeRole"
                ],
                "condition": [],
                "effect": "Allow",
                "not_actions": [],
                "not_principals": [],
                "not_resources": [],
                "principals": [
                  {
                    "identifiers": [
                      "lambda.amazonaws.com"
                    ],
                    "type": "Service"
                  }
                ],
                "resources": [],
                "sid": ""
              }
            ],
            "version": "2012-10-17"
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "aws_region",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "current": null,
            "description": "Europe (Frankfurt)",
            "endpoint": "ec2.eu-central-1.amazonaws.com",
            "id": "eu-central-1",
            "name": "eu-central-1"
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "template_file",
      "name": "base_lambda",
      "provider": "provider[\"registry.terraform.io/hashicorp/template\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "filename": null,
            "id": "d9266c832322038a783df54160984fc62d043799e87efa2684cdadb2875ec098",
            "rendered": "{\r\n    \"Version\": \"2012-10-17\",\r\n    \"Statement\": [\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogGroup\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:eu-central-1:689491089140:*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogStream\",\r\n                \"logs:PutLogEvents\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:eu-central-1:689491089140:log-group:/aws/lambda/datdl-ppm-*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"ec2:CreateNetworkInterface\",\r\n                \"ec2:DescribeNetworkInterfaces\",\r\n                \"ec2:DeleteNetworkInterface\"\r\n            ],\r\n            \"Resource\": [\r\n                \"*\"\r\n            ]\r\n        }\r\n    ]\r\n}\r\n",
            "template": "{\r\n    \"Version\": \"2012-10-17\",\r\n    \"Statement\": [\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogGroup\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:${region}:${account_id}:*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogStream\",\r\n                \"logs:PutLogEvents\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:${region}:${account_id}:log-group:/aws/lambda/${namespace}-*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"ec2:CreateNetworkInterface\",\r\n                \"ec2:DescribeNetworkInterfaces\",\r\n                \"ec2:DeleteNetworkInterface\"\r\n            ],\r\n            \"Resource\": [\r\n                \"*\"\r\n            ]\r\n        }\r\n    ]\r\n}\r\n",
            "vars": {
              "account_id": "689491089140",
              "namespace": "datdl-ppm",
              "region": "eu-central-1",
              "region_short_code": "euc1",
              "stage": "dev"
            }
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "template_file",
      "name": "redshift_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/template\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "filename": null,
            "id": "6054d5db0be868efdb10e5a96ced0e92ee42cf0918f89eb4797b41dea5f2e016",
            "rendered": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"kms:Decrypt\",\n            \"kms:GetKeyPolicy\",\n            \"kms:DescribeKey\"\n         ],\n         \"Resource\":[\n            \"arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4\"\n         ]\n      },\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"ssm:GetParameters\",\n            \"ssm:GetParameter\",\n            \"ssm:GetParameterHistory\"\n         ],\n         \"Resource\":[\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLPassword\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLCoreIngestUserName\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLReportIngestPassword\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLReportIngestUsername\"\n         ]\n      }\n   ]\n}\n",
            "template": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"kms:Decrypt\",\n            \"kms:GetKeyPolicy\",\n            \"kms:DescribeKey\"\n         ],\n         \"Resource\":[\n            \"arn:aws:kms:${region}:${account_id}:key/${kms_key}\"\n         ]\n      },\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"ssm:GetParameters\",\n            \"ssm:GetParameter\",\n            \"ssm:GetParameterHistory\"\n         ],\n         \"Resource\":[\n            \"arn:aws:ssm:${region}:${account_id}:parameter/${ssm_ppm_redshift_datdl_core_password}\",\n            \"arn:aws:ssm:${region}:${account_id}:parameter/${ssm_ppm_redshift_datdl_core_user_name}\",\n            \"arn:aws:ssm:${region}:${account_id}:parameter/${ssm_ppm_redshift_datdl_report_password}\",\n            \"arn:aws:ssm:${region}:${account_id}:parameter/${ssm_ppm_redshift_datdl_report_user_name}\"\n         ]\n      }\n   ]\n}\n",
            "vars": {
              "account_id": "689491089140",
              "kms_key": "mrk-239209187b514a3da03155ca4a1e8fa4",
              "region": "eu-central-1",
              "region_short_code": "euc1",
              "ssm_ppm_redshift_datdl_core_password": "PPMRedshiftDATDLPassword",
              "ssm_ppm_redshift_datdl_core_user_name": "PPMRedshiftDATDLCoreIngestUserName",
              "ssm_ppm_redshift_datdl_report_password": "PPMRedshiftDATDLReportIngestPassword",
              "ssm_ppm_redshift_datdl_report_user_name": "PPMRedshiftDATDLReportIngestUsername"
            }
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "template_file",
      "name": "source_bucket_read_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/template\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "filename": null,
            "id": "e5353e3b361f1d937deda1c8fe2f8bd602e60d54743c414dca04fc04d8f22daa",
            "rendered": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Get*\",\n            \"s3:List*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1\",\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1/*\"\n         ]\n      }\n   ]\n}\n",
            "template": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Get*\",\n            \"s3:List*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::${bucket}\",\n            \"arn:aws:s3:::${bucket}/*\"\n         ]\n      }\n   ]\n}\n",
            "vars": {
              "bucket": "datdl-ppm-s3bronze-dev-euc1",
              "region_short_code": "euc1"
            }
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "data",
      "type": "template_file",
      "name": "source_bucket_write_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/template\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "filename": null,
            "id": "a9942a40309bfa126f3f3cab01aaa4f167b70eac882be7f1062f7f0d4a9bbc40",
            "rendered": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Put*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1\",\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1/*\"\n         ]\n      }\n   ]\n}\n",
            "template": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Put*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::${bucket}\",\n            \"arn:aws:s3:::${bucket}/*\"\n         ]\n      }\n   ]\n}\n",
            "vars": {
              "bucket": "datdl-ppm-s3bronze-dev-euc1",
              "region_short_code": "euc1"
            }
          }
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy",
      "name": "base_lambda",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambdabase-dev-euc1",
            "description": "Base policy for lambda functions",
            "id": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambdabase-dev-euc1",
            "name": "datdl-ppm-policy-lambdabase-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "policy": "{\r\n    \"Version\": \"2012-10-17\",\r\n    \"Statement\": [\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogGroup\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:eu-central-1:689491089140:*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"logs:CreateLogStream\",\r\n                \"logs:PutLogEvents\"\r\n            ],\r\n            \"Resource\": [\r\n                \"arn:aws:logs:eu-central-1:689491089140:log-group:/aws/lambda/datdl-ppm-*\"\r\n            ]\r\n        },\r\n        {\r\n            \"Effect\": \"Allow\",\r\n            \"Action\": [\r\n                \"ec2:CreateNetworkInterface\",\r\n                \"ec2:DescribeNetworkInterfaces\",\r\n                \"ec2:DeleteNetworkInterface\"\r\n            ],\r\n            \"Resource\": [\r\n                \"*\"\r\n            ]\r\n        }\r\n    ]\r\n}\r\n"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.template_file.base_lambda"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy",
      "name": "redshift_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-redshift-readwrite-dev-euc1",
            "description": "Policy for lambda functions to access Redshift credentials with KMS",
            "id": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-redshift-readwrite-dev-euc1",
            "name": "datdl-ppm-policy-lambda-redshift-readwrite-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "policy": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"kms:Decrypt\",\n            \"kms:GetKeyPolicy\",\n            \"kms:DescribeKey\"\n         ],\n         \"Resource\":[\n            \"arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4\"\n         ]\n      },\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"ssm:GetParameters\",\n            \"ssm:GetParameter\",\n            \"ssm:GetParameterHistory\"\n         ],\n         \"Resource\":[\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLPassword\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLCoreIngestUserName\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLReportIngestPassword\",\n            \"arn:aws:ssm:eu-central-1:689491089140:parameter/PPMRedshiftDATDLReportIngestUsername\"\n         ]\n      }\n   ]\n}\n"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.template_file.redshift_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy",
      "name": "source_bucket_read_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-read-dev-euc1",
            "description": "Policy to Expose the source(bronze) bucket for the ELT job(e.g. lambda) to read from it.",
            "id": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-read-dev-euc1",
            "name": "datdl-ppm-policy-lambda-s3bronze-read-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "policy": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Get*\",\n            \"s3:List*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1\",\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1/*\"\n         ]\n      }\n   ]\n}\n"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.template_file.source_bucket_read_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy",
      "name": "source_bucket_write_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-write-dev-euc1",
            "description": "Policy to Expose the source(bronze) bucket for the ELT job(e.g. lambda) to write to it.",
            "id": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-write-dev-euc1",
            "name": "datdl-ppm-policy-lambda-s3bronze-write-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "policy": "{\n   \"Version\":\"2012-10-17\",\n   \"Statement\":[\n      {\n         \"Effect\":\"Allow\",\n         \"Action\":[\n            \"s3:Put*\"\n         ],\n         \"Resource\":[\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1\",\n            \"arn:aws:s3:::datdl-ppm-s3bronze-dev-euc1/*\"\n         ]\n      }\n   ]\n}\n"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.template_file.source_bucket_write_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy_attachment",
      "name": "base_lambda",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "groups": [],
            "id": "datdl-ppm-policyattachmet-lambdabase-dev-euc1",
            "name": "datdl-ppm-policyattachmet-lambdabase-dev-euc1",
            "policy_arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambdabase-dev-euc1",
            "roles": [
              "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1",
              "datdl-ppm-role-lambdabase-dev-euc1"
            ],
            "users": []
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.aws_iam_policy.base_lambda",
            "module.aws_iam.aws_iam_role.base_lambda",
            "module.aws_iam.aws_iam_role.redshift_access",
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy",
            "module.aws_iam.data.template_file.base_lambda"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy_attachment",
      "name": "redshift_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "groups": [],
            "id": "datdl-ppm-policyattachmet-lambda-redshift-readwrite-dev-euc1",
            "name": "datdl-ppm-policyattachmet-lambda-redshift-readwrite-dev-euc1",
            "policy_arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-redshift-readwrite-dev-euc1",
            "roles": [
              "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1"
            ],
            "users": []
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.aws_iam_policy.redshift_access",
            "module.aws_iam.aws_iam_role.redshift_access",
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy",
            "module.aws_iam.data.template_file.redshift_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy_attachment",
      "name": "source_bucket_read_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "groups": [],
            "id": "datdl-ppm-policyattachmet-lambda-s3bronze-dev-euc1",
            "name": "datdl-ppm-policyattachmet-lambda-s3bronze-dev-euc1",
            "policy_arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-read-dev-euc1",
            "roles": [
              "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1"
            ],
            "users": []
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.aws_iam_policy.source_bucket_read_access",
            "module.aws_iam.aws_iam_role.redshift_access",
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy",
            "module.aws_iam.data.template_file.source_bucket_read_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_policy_attachment",
      "name": "source_bucket_write_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "groups": [],
            "id": "datdl-ppm-policyattachment-lambda-s3bronze-write-dev-euc1",
            "name": "datdl-ppm-policyattachment-lambda-s3bronze-write-dev-euc1",
            "policy_arn": "arn:aws:iam::689491089140:policy/datdl-ppm-policy-lambda-s3bronze-write-dev-euc1",
            "roles": [
              "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1"
            ],
            "users": []
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.aws_iam_policy.source_bucket_write_access",
            "module.aws_iam.aws_iam_role.redshift_access",
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy",
            "module.aws_iam.data.template_file.source_bucket_write_access"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_role",
      "name": "base_lambda",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:role/datdl-ppm-role-lambdabase-dev-euc1",
            "assume_role_policy": "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"\",\"Effect\":\"Allow\",\"Principal\":{\"Service\":\"lambda.amazonaws.com\"},\"Action\":\"sts:AssumeRole\"}]}",
            "create_date": "2023-08-17T07:53:39Z",
            "description": "Base role for lambda functions",
            "force_detach_policies": false,
            "id": "datdl-ppm-role-lambdabase-dev-euc1",
            "max_session_duration": 3600,
            "name": "datdl-ppm-role-lambdabase-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "permissions_boundary": "arn:aws:iam::689491089140:policy/permissions-environment-admin-boundary",
            "tags": {
              "ManagedBy": "environment-operator",
              "business_responsible": "oswald.yinyeh@telefonica.com",
              "company": "TCS",
              "environment": "dev",
              "project": "DAT Platform",
              "technical_author_responsible": "Oswald Yinyeh",
              "technical_responsible": "TEF_OSS_DAT_Team@telefonica.com"
            },
            "unique_id": "AROA2BCG7C32I4NNNV2JQ"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy"
          ]
        }
      ]
    },
    {
      "module": "module.aws_iam",
      "mode": "managed",
      "type": "aws_iam_role",
      "name": "redshift_access",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:iam::689491089140:role/datdl-ppm-role-lambda-readshift-readwrite-dev-euc1",
            "assume_role_policy": "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"\",\"Effect\":\"Allow\",\"Principal\":{\"Service\":\"lambda.amazonaws.com\"},\"Action\":\"sts:AssumeRole\"}]}",
            "create_date": "2023-08-17T07:53:39Z",
            "description": "Role for lambda functions that need to access Redshift and read/write to source bucket",
            "force_detach_policies": false,
            "id": "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1",
            "max_session_duration": 3600,
            "name": "datdl-ppm-role-lambda-readshift-readwrite-dev-euc1",
            "name_prefix": null,
            "path": "/",
            "permissions_boundary": "arn:aws:iam::689491089140:policy/permissions-environment-admin-boundary",
            "tags": {
              "ManagedBy": "environment-operator",
              "business_responsible": "oswald.yinyeh@telefonica.com",
              "company": "TCS",
              "environment": "dev",
              "project": "DAT Platform",
              "technical_author_responsible": "Oswald Yinyeh",
              "technical_responsible": "TEF_OSS_DAT_Team@telefonica.com"
            },
            "unique_id": "AROA2BCG7C32N7VHLFENY"
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_iam.data.aws_iam_policy_document.lambda-assume-role-policy"
          ]
        }
      ]
    },
    {
      "module": "module.aws_lambda_function",
      "mode": "data",
      "type": "archive_file",
      "name": "datdl-ppm-lambdaelt-dev-euc1-zip",
      "provider": "provider[\"registry.terraform.io/hashicorp/archive\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "exclude_symlink_directories": null,
            "excludes": null,
            "id": "84ae265b50df9142b32bbd7e56aca9759917bd3c",
            "output_base64sha256": "YC/8nPKGoVmXdOr65hBnHSlV9ulilXN+yQHOXjqDFs8=",
            "output_base64sha512": "yW2Jbr+gQKryKsEkORf8W1VsDKv3G5jU946WBXLsdwLqtWCV5H0uMc7id1qgCSiobh/LgSFhlMKwDWuv2vo9Cg==",
            "output_file_mode": null,
            "output_md5": "586d21594f88cb859fcd0e096c0b5ed0",
            "output_path": "../../../../../../dl/aws/src/ppm/python/lambda/datdl-ppm-lambdaelt-dev-euc1.zip",
            "output_sha": "84ae265b50df9142b32bbd7e56aca9759917bd3c",
            "output_sha256": "602ffc9cf286a1599774eafae610671d2955f6e96295737ec901ce5e3a8316cf",
            "output_sha512": "c96d896ebfa040aaf22ac1243917fc5b556c0cabf71b98d4f78e960572ec7702eab56095e47d2e31cee2775aa00928a86e1fcb81216194c2b00d6bafdafa3d0a",
            "output_size": 4138,
            "source": [],
            "source_content": null,
            "source_content_filename": null,
            "source_dir": "../../../../../../dl/aws/src/ppm/python/lambda/",
            "source_file": null,
            "type": "zip"
          }
        }
      ]
    },
    {
      "module": "module.aws_lambda_function",
      "mode": "data",
      "type": "aws_caller_identity",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "account_id": "689491089140",
            "arn": "arn:aws:sts::689491089140:assumed-role/dat_gitlab_cicd_authentication/AssumeRoleSession1",
            "id": "2023-08-23 14:22:02.0194186 +0000 UTC",
            "user_id": "AROA2BCG7C32FRGKERJ72:AssumeRoleSession1"
          }
        }
      ]
    },
    {
      "module": "module.aws_lambda_function",
      "mode": "data",
      "type": "aws_region",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "current": null,
            "description": "Europe (Frankfurt)",
            "endpoint": "ec2.eu-central-1.amazonaws.com",
            "id": "eu-central-1",
            "name": "eu-central-1"
          }
        }
      ]
    },
    {
      "module": "module.aws_lambda_function",
      "mode": "managed",
      "type": "aws_lambda_function",
      "name": "datdl-ppm-lambdaelt-dev-euc1",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:lambda:eu-central-1:689491089140:function:datdl-ppm-lambdaelt-dev-euc1",
            "dead_letter_config": [],
            "description": "",
            "environment": [],
            "file_system_config": [],
            "filename": "../../../../../../dl/aws/src/ppm/python/lambda/datdl-ppm-lambdaelt-dev-euc1.zip",
            "function_name": "datdl-ppm-lambdaelt-dev-euc1",
            "handler": "src.ppm.python.lambda.redshift_copy.handler.handle",
            "id": "datdl-ppm-lambdaelt-dev-euc1",
            "invoke_arn": "arn:aws:apigateway:eu-central-1:lambda:path/2015-03-31/functions/arn:aws:lambda:eu-central-1:689491089140:function:datdl-ppm-lambdaelt-dev-euc1/invocations",
            "kms_key_arn": "",
            "last_modified": "2023-08-23T14:18:20.000+0000",
            "layers": [],
            "memory_size": 128,
            "publish": true,
            "qualified_arn": "arn:aws:lambda:eu-central-1:689491089140:function:datdl-ppm-lambdaelt-dev-euc1:24",
            "reserved_concurrent_executions": -1,
            "role": "arn:aws:iam::689491089140:role/datdl-ppm-role-lambdabase-dev-euc1",
            "runtime": "python3.7",
            "s3_bucket": null,
            "s3_key": null,
            "s3_object_version": null,
            "source_code_hash": "YC/8nPKGoVmXdOr65hBnHSlV9ulilXN+yQHOXjqDFs8=",
            "source_code_size": 4138,
            "tags": {
              "ManagedBy": "environment-operator",
              "business_responsible": "oswald.yinyeh@telefonica.com",
              "company": "TCS",
              "environment": "dev",
              "project": "DAT Platform",
              "technical_author_responsible": "Oswald Yinyeh",
              "technical_responsible": "TEF_OSS_DAT_Team@telefonica.com"
            },
            "timeout": 3,
            "timeouts": null,
            "tracing_config": [
              {
                "mode": "PassThrough"
              }
            ],
            "version": "24",
            "vpc_config": []
          },
          "private": "eyJlMmJmYjczMC1lY2FhLTExZTYtOGY4OC0zNDM2M2JjN2M0YzAiOnsiY3JlYXRlIjo2MDAwMDAwMDAwMDB9fQ==",
          "dependencies": [
            "module.aws_lambda_function.data.archive_file.datdl-ppm-lambdaelt-dev-euc1-zip"
          ]
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "data",
      "type": "aws_secretsmanager_secret",
      "name": "aws_redshiftdwh_secretsmanager_masterdb",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "description": "",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "kms_key_id": "arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4",
            "name": "datdl-usercreds-manager-redshiftdwh-dev-euc1",
            "policy": "",
            "rotation_enabled": false,
            "rotation_lambda_arn": "",
            "rotation_rules": [],
            "tags": {}
          }
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "data",
      "type": "aws_secretsmanager_secret_version",
      "name": "aws_redshiftdwh_secretsmanager_secrets_creds_versions",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT|AWSCURRENT",
            "secret_binary": "",
            "secret_id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "secret_string": "   {\r\n    \"username\": \"datdl_admin_dev\",\r\n    \"password\": \"{N4R_y9*o%O}j-fo\"\r\n   }\r\n",
            "version_id": "A5CD9919-4266-4676-A6CB-A38826F434D6",
            "version_stage": "AWSCURRENT",
            "version_stages": [
              "AWSCURRENT"
            ]
          }
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "aws_redshift_cluster",
      "name": "this",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "allow_version_upgrade": true,
            "arn": "arn:aws:redshift:eu-central-1:689491089140:cluster:datdl-redshiftdwh-dev-euc1",
            "automated_snapshot_retention_period": 0,
            "availability_zone": "eu-central-1a",
            "bucket_name": null,
            "cluster_identifier": "datdl-redshiftdwh-dev-euc1",
            "cluster_parameter_group_name": "datdl-redshiftdwh-dev-euc1-redshift-1-0-custom-params",
            "cluster_public_key": "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCXGPZNg14csCyV00RvC5CipMhQhLPfVFBwQKhYFsB9l+ZLJbYq4Miw0cysLV0nZZjGuDXCo7RHFESGhr7glBSjlNFh6xgz0Zq1mZmvIeF269PTdIimbwZ/DqGVWag/hhBM0D5ILoRbpgfIit/fVj8R4DlrOU2PgSI77g50cKPSWLvZkSBFA/LFXepAR/0EjcrolnXxbAXAoFNOAfDLurnndzY37QsZazFnMkq/MRpEFAilN078Kgw3fyH/iCZaMt65ZADXl1tED2+3/TC84unhh+dGU8jatgenGIB5xzgYxgv3U6Wg4Qs1lNuw6vadi/fvBX1afYoVdqVDOG9LkaUx Amazon-Redshift\n",
            "cluster_revision_number": "54899",
            "cluster_security_groups": [],
            "cluster_subnet_group_name": "datdl-redshiftdwh-dev-euc1",
            "cluster_type": "single-node",
            "cluster_version": "1.0",
            "database_name": "datdl_ppm_dev_euc1",
            "dns_name": "datdl-redshiftdwh-dev-euc1.c4fx5apw1s28.eu-central-1.redshift.amazonaws.com",
            "elastic_ip": null,
            "enable_logging": null,
            "encrypted": false,
            "endpoint": "datdl-redshiftdwh-dev-euc1.c4fx5apw1s28.eu-central-1.redshift.amazonaws.com:5439",
            "enhanced_vpc_routing": false,
            "final_snapshot_identifier": "false",
            "iam_roles": [],
            "id": "datdl-redshiftdwh-dev-euc1",
            "kms_key_id": "",
            "logging": [
              {
                "bucket_name": "",
                "enable": false,
                "s3_key_prefix": ""
              }
            ],
            "master_password": "{N4R_y9*o%O}j-fo",
            "master_username": "datdl_admin_dev",
            "node_type": "dc2.large",
            "number_of_nodes": 1,
            "owner_account": null,
            "port": 5439,
            "preferred_maintenance_window": "sat:10:00-sat:10:30",
            "publicly_accessible": false,
            "s3_key_prefix": null,
            "skip_final_snapshot": true,
            "snapshot_cluster_identifier": null,
            "snapshot_copy": [],
            "snapshot_identifier": null,
            "tags": {
              "ManagedBy": "environment-operator",
              "business_responsible": "oswald.yinyeh@telefonica.com",
              "company": "TCS",
              "environment": "dev",
              "project": "DAT Platform",
              "technical_author_responsible": "Oswald Yinyeh",
              "technical_responsible": "TEF_OSS_DAT_Team@telefonica.com"
            },
            "timeouts": null,
            "vpc_security_group_ids": [
              "sg-0ca334a9c9049012f"
            ]
          },
          "private": "eyJlMmJmYjczMC1lY2FhLTExZTYtOGY4OC0zNDM2M2JjN2M0YzAiOnsiY3JlYXRlIjo0NTAwMDAwMDAwMDAwLCJkZWxldGUiOjI0MDAwMDAwMDAwMDAsInVwZGF0ZSI6MjQwMDAwMDAwMDAwMH19",
          "dependencies": [
            "module.aws_redshift.aws_redshift_parameter_group.this",
            "module.aws_redshift.aws_redshift_subnet_group.this",
            "module.aws_redshift.data.aws_secretsmanager_secret_version.aws_redshiftdwh_secretsmanager_secrets_creds_versions"
          ]
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "aws_redshift_parameter_group",
      "name": "this",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "index_key": 0,
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:redshift:eu-central-1:689491089140:parametergroup:datdl-redshiftdwh-dev-euc1-redshift-1-0-custom-params",
            "description": "Managed by Terraform",
            "family": "redshift-1.0",
            "id": "datdl-redshiftdwh-dev-euc1-redshift-1-0-custom-params",
            "name": "datdl-redshiftdwh-dev-euc1-redshift-1-0-custom-params",
            "parameter": [
              {
                "name": "enable_user_activity_logging",
                "value": "false"
              },
              {
                "name": "require_ssl",
                "value": "false"
              },
              {
                "name": "use_fips_ssl",
                "value": "false"
              },
              {
                "name": "wlm_json_configuration",
                "value": "[{\"query_concurrency\": 5}]"
              }
            ],
            "tags": {}
          },
          "private": "bnVsbA=="
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "aws_redshift_subnet_group",
      "name": "this",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "index_key": 0,
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:redshift:eu-central-1:689491089140:subnetgroup:datdl-redshiftdwh-dev-euc1",
            "description": "Redshift subnet group of datdl-redshiftdwh-dev-euc1",
            "id": "datdl-redshiftdwh-dev-euc1",
            "name": "datdl-redshiftdwh-dev-euc1",
            "subnet_ids": [
              "subnet-063f0de51e67d6a56",
              "subnet-0db3fc4b1a19c3d00"
            ],
            "tags": {
              "ManagedBy": "environment-operator",
              "business_responsible": "oswald.yinyeh@telefonica.com",
              "company": "TCS",
              "environment": "dev",
              "project": "DAT Platform",
              "technical_author_responsible": "Oswald Yinyeh",
              "technical_responsible": "TEF_OSS_DAT_Team@telefonica.com"
            }
          },
          "private": "bnVsbA=="
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "aws_secretsmanager_secret",
      "name": "aws_redshiftdwh_secretsmanager_masterdb",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "description": "",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "kms_key_id": "arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4",
            "name": "datdl-usercreds-manager-redshiftdwh-dev-euc1",
            "name_prefix": null,
            "policy": null,
            "recovery_window_in_days": 30,
            "rotation_enabled": false,
            "rotation_lambda_arn": "",
            "rotation_rules": [],
            "tags": {}
          },
          "private": "bnVsbA=="
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "aws_secretsmanager_secret_version",
      "name": "aws_redshiftdwh_secretsmanager_secrets_versions",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT|A5CD9919-4266-4676-A6CB-A38826F434D6",
            "secret_binary": "",
            "secret_id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-redshiftdwh-dev-euc1-1WhBsT",
            "secret_string": "   {\r\n    \"username\": \"datdl_admin_dev\",\r\n    \"password\": \"{N4R_y9*o%O}j-fo\"\r\n   }\r\n",
            "version_id": "A5CD9919-4266-4676-A6CB-A38826F434D6",
            "version_stages": [
              "AWSCURRENT"
            ]
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_redshift.aws_secretsmanager_secret.aws_redshiftdwh_secretsmanager_masterdb",
            "module.aws_redshift.random_password.password"
          ]
        }
      ]
    },
    {
      "module": "module.aws_redshift",
      "mode": "managed",
      "type": "random_password",
      "name": "password",
      "provider": "provider[\"registry.terraform.io/hashicorp/random\"]",
      "instances": [
        {
          "schema_version": 3,
          "attributes": {
            "bcrypt_hash": "$2a$10$hWzq7DXghUFe0UunerP3ge2HwVl7.sE.blzJUPfPqRjGwjOoafuwO",
            "id": "none",
            "keepers": null,
            "length": 16,
            "lower": true,
            "min_lower": 0,
            "min_numeric": 0,
            "min_special": 0,
            "min_upper": 0,
            "number": true,
            "numeric": true,
            "override_special": "!$%\u0026*()-_=+[]{}\u003c\u003e:?",
            "result": "{N4R_y9*o%O}j-fo",
            "special": true,
            "upper": true
          }
        }
      ]
    },
    {
      "module": "module.aws_s3",
      "mode": "data",
      "type": "aws_s3_bucket",
      "name": "existing_bucket",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:s3:::dattdl-dev-processed-oswaldtest",
            "bucket": "dattdl-dev-processed-oswaldtest",
            "bucket_domain_name": "dattdl-dev-processed-oswaldtest.s3.amazonaws.com",
            "bucket_regional_domain_name": "dattdl-dev-processed-oswaldtest.s3.eu-central-1.amazonaws.com",
            "hosted_zone_id": "Z21DNDUVLTQW6Q",
            "id": "dattdl-dev-processed-oswaldtest",
            "region": "eu-central-1",
            "website_domain": null,
            "website_endpoint": null
          }
        }
      ]
    },
    {
      "module": "module.aws_s3",
      "mode": "data",
      "type": "aws_s3_bucket",
      "name": "sagemaker_bucket",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:s3:::sagemaker-eu-central-1-689491089140",
            "bucket": "sagemaker-eu-central-1-689491089140",
            "bucket_domain_name": "sagemaker-eu-central-1-689491089140.s3.amazonaws.com",
            "bucket_regional_domain_name": "sagemaker-eu-central-1-689491089140.s3.eu-central-1.amazonaws.com",
            "hosted_zone_id": "Z21DNDUVLTQW6Q",
            "id": "sagemaker-eu-central-1-689491089140",
            "region": "eu-central-1",
            "website_domain": null,
            "website_endpoint": null
          }
        }
      ]
    },
    {
      "module": "module.aws_s3",
      "mode": "managed",
      "type": "aws_s3_bucket",
      "name": "sagemaker_bucket",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "acceleration_status": "",
            "acl": "private",
            "arn": "arn:aws:s3:::sagemaker-eu-central-1-689491089140",
            "bucket": "sagemaker-eu-central-1-689491089140",
            "bucket_domain_name": "sagemaker-eu-central-1-689491089140.s3.amazonaws.com",
            "bucket_prefix": "",
            "bucket_regional_domain_name": "sagemaker-eu-central-1-689491089140.s3.eu-central-1.amazonaws.com",
            "cors_rule": [],
            "force_destroy": false,
            "grant": [
              {
                "id": "78ff663ff74550c3aa751297ba86fa45b0d5e69dad1d4c56e03facc68069d02e",
                "permissions": [
                  "FULL_CONTROL"
                ],
                "type": "CanonicalUser",
                "uri": ""
              }
            ],
            "hosted_zone_id": "Z21DNDUVLTQW6Q",
            "id": "sagemaker-eu-central-1-689491089140",
            "lifecycle_rule": [],
            "logging": [],
            "object_lock_configuration": [],
            "object_lock_enabled": false,
            "policy": "",
            "region": "eu-central-1",
            "replication_configuration": [],
            "request_payer": "BucketOwner",
            "server_side_encryption_configuration": [
              {
                "rule": [
                  {
                    "apply_server_side_encryption_by_default": [
                      {
                        "kms_master_key_id": "arn:aws:kms:eu-central-1:689491089140:key/1860c4f0-5d5b-4d76-b7fc-eabfaf557a00",
                        "sse_algorithm": "aws:kms"
                      }
                    ],
                    "bucket_key_enabled": false
                  }
                ]
              }
            ],
            "tags": {},
            "tags_all": {},
            "timeouts": null,
            "versioning": [
              {
                "enabled": false,
                "mfa_delete": false
              }
            ],
            "website": [],
            "website_domain": null,
            "website_endpoint": null
          },
          "private": "eyJlMmJmYjczMC1lY2FhLTExZTYtOGY4OC0zNDM2M2JjN2M0YzAiOnsiY3JlYXRlIjoxMjAwMDAwMDAwMDAwLCJkZWxldGUiOjM2MDAwMDAwMDAwMDAsInJlYWQiOjEyMDAwMDAwMDAwMDAsInVwZGF0ZSI6MTIwMDAwMDAwMDAwMH19"
        }
      ]
    },
    {
      "module": "module.aws_ssm",
      "mode": "data",
      "type": "aws_secretsmanager_secret",
      "name": "secretmasterDB",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "description": "",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "kms_key_id": "arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4",
            "name": "datdl-usercreds-manager-dev-euc1",
            "policy": "",
            "rotation_enabled": false,
            "rotation_lambda_arn": "",
            "rotation_rules": [],
            "tags": {}
          }
        }
      ]
    },
    {
      "module": "module.aws_ssm",
      "mode": "data",
      "type": "aws_secretsmanager_secret_version",
      "name": "creds",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY|AWSCURRENT",
            "secret_binary": "",
            "secret_id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "secret_string": "   {\r\n    \"username\": \"datdl-admin-dev\",\r\n    \"password\": \"NdWi*Bb4Orzi%iF!\"\r\n   }\r\n",
            "version_id": "2C3D692C-C724-4603-8FA0-50C003927EFC",
            "version_stage": "AWSCURRENT",
            "version_stages": [
              "AWSCURRENT"
            ]
          }
        }
      ]
    },
    {
      "module": "module.aws_ssm",
      "mode": "managed",
      "type": "aws_secretsmanager_secret",
      "name": "secretmasterDB",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "description": "",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "kms_key_id": "arn:aws:kms:eu-central-1:689491089140:key/mrk-239209187b514a3da03155ca4a1e8fa4",
            "name": "datdl-usercreds-manager-dev-euc1",
            "name_prefix": null,
            "policy": null,
            "recovery_window_in_days": 30,
            "rotation_enabled": false,
            "rotation_lambda_arn": "",
            "rotation_rules": [],
            "tags": {}
          },
          "private": "bnVsbA=="
        }
      ]
    },
    {
      "module": "module.aws_ssm",
      "mode": "managed",
      "type": "aws_secretsmanager_secret_version",
      "name": "sversion",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "arn": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY|2C3D692C-C724-4603-8FA0-50C003927EFC",
            "secret_binary": "",
            "secret_id": "arn:aws:secretsmanager:eu-central-1:689491089140:secret:datdl-usercreds-manager-dev-euc1-e5cadY",
            "secret_string": "   {\r\n    \"username\": \"datdl-admin-dev\",\r\n    \"password\": \"NdWi*Bb4Orzi%iF!\"\r\n   }\r\n",
            "version_id": "2C3D692C-C724-4603-8FA0-50C003927EFC",
            "version_stages": [
              "AWSCURRENT"
            ]
          },
          "private": "bnVsbA==",
          "dependencies": [
            "module.aws_ssm.aws_secretsmanager_secret.secretmasterDB",
            "module.aws_ssm.random_password.password"
          ]
        }
      ]
    },
    {
      "module": "module.aws_ssm",
      "mode": "managed",
      "type": "random_password",
      "name": "password",
      "provider": "provider[\"registry.terraform.io/hashicorp/random\"]",
      "instances": [
        {
          "schema_version": 3,
          "attributes": {
            "bcrypt_hash": "$2a$10$I88qFT0zje9hUh/nYr.Xguh/H.jYURwFTxh0/pGR6Q08QNFT19sKG",
            "id": "none",
            "keepers": null,
            "length": 16,
            "lower": true,
            "min_lower": 0,
            "min_numeric": 0,
            "min_special": 0,
            "min_upper": 0,
            "number": true,
            "numeric": true,
            "override_special": "!$%\u0026*()-_=+[]{}\u003c\u003e:?",
            "result": "NdWi*Bb4Orzi%iF!",
            "special": true,
            "upper": true
          }
        }
      ]
    }
  ]
}





resource "aws_s3_bucket" "example_bucket" {
  acl    = "private" # Replace with your desired access control settings
  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm     = "aws:kms"
        kms_master_key_id = "arn:aws:kms:eu-central-1:689491089140:key/1860c4f0-5d5b-4d76-b7fc-eabfaf557a00"
      }
    }
  }
}

data "aws_s3_bucket" "existing_bucket" {
  bucket = "dattdl-dev-processed-oswaldtest" # The name of your S3 bucket
}
