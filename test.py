based on the below script IAM role cannot be created so I'm having a exisitng IAM role I want to insert the IAM code provide the updated code accordingly

variable "database" {}

resource "aws_glue_job" "create_table_job" {
  name            = "create_table_job"
  role_arn        = aws_iam_role.example.arn
  command {
    name        = "pythonshell"  # Change the command if needed
    script_location = "s3://dat-infra-default-athena/glue-etl-scripts/script.py"  # Update with your S3 script location
  }
  default_arguments = {
    "--enable-glue-datacatalog" = "true" # Enable Glue Data Catalog
    "--database_name"           = var.database
    "--table_name"              = "table1"
    "--s3_target_path"          = "s3://gold-datdl-glue-lakeformat/grip_t_dot_open/_symlink_format_manifest/"
  }
}

resource "aws_iam_role" "example" {
  # Define your IAM role here, allowing Glue permissions as needed
}

resource "aws_glue_trigger" "execute_sql_script" {
  name         = "execute_sql_script"
  type         = "CONDITIONAL"
  workflow_name = aws_glue_job.create_table_job.name

  actions {
    job_name = aws_glue_job.create_table_job.name
    arguments = {}
  }
}
