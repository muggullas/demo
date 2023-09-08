variable "database" {}

# Define the SQL queries you want to execute
variable "queries" {
  type    = list(string)
  default = [
    "CREATE EXTERNAL TABLE IF NOT EXISTS database1.table1 (...)",
    -- Add more SQL queries here
  ]
}

resource "aws_athena_named_query" "example_named_query" {
  count    = length(var.queries)
  name     = "example_named_query_${count.index + 1}"
  database = "datdl_ppm_gluecatalogdbname_dev_euc1_test_one"
  query    = var.queries[count.index]
  workgroup = aws_athena_workgroup.athena_workgroup.name
}

# Define the database variable
database = "datdl_ppm_gluecatalogdbname_dev_euc1_test_one"

# Define the list of SQL queries
queries = [
  "CREATE EXTERNAL TABLE IF NOT EXISTS database1.table1 (...)",
  "CREATE TABLE IF NOT EXISTS database1.table2 (...)",
  -- Add more SQL queries here
]
