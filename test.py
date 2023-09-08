based on the below code i want to execute the below sql code in the query parameter and i want execute more queries also in the query section provide the code accrodingly



resource "aws_athena_named_query" "example_named_query" {
  name     = "example_named_query"
  database = "datdl_ppm_gluecatalogdbname_dev_euc1_test_one"
  query    = "select * from AwsDataCatalog.datdl_ppm_gluecatalogdbname_dev_euc1.grip_t_dot_open_test_one;"  # Use the correct table name
  workgroup = aws_athena_workgroup.athena_workgroup.name
}

sql code:

    CREATE EXTERNAL TABLE IF NOT EXISTS database1.table1(
      status string COMMENT 'Status of the task',
      assignee string COMMENT 'Assignee of the task',
      new_feature_link string COMMENT 'Link to the new feature',
      updated string COMMENT 'Updated timestamp',
      priority string COMMENT 'Priority of the task',
      insert_date string COMMENT 'Date of insertion',
      reporter string COMMENT 'Reporter of the task',
      resolutiondate string COMMENT 'Resolution date',
      story_point BINARY COMMENT 'Story point as binary',
      created string COMMENT 'Creation timestamp',
      type string COMMENT 'Type of task',
      identifier string COMMENT 'Task identifier',
      summary string COMMENT 'Task summary',
      sprint string COMMENT 'Sprint information',
      component string COMMENT 'Component information',
      resolution string COMMENT 'Resolution details'
    )
    PARTITIONED BY (year string, month string, day string)
    ROW FORMAT SERDE 'org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe'
    STORED AS INPUTFORMAT 'org.apache.hadoop.hive.ql.io.SymlinkTextInputFormat'
    OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
    LOCATION 's3://gold-datdl-glue-lakeformat/grip_t_dot_open/_symlink_format_manifest/';
