# Set whether to create an instance
create = true

# Specify the name for the EC2 instance
name = "my-instance-name"

# Specify the SSM parameter name for the AMI ID
ami_ssm_parameter = "/aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2"

# Specify whether to associate a public IP address with the instance in a VPC
associate_public_ip_address = true

# Specify the availability zone for the instance (uncomment and specify a value if needed)
availability_zone = "us-west-2a"

# Specify whether the launched EC2 instance will be EBS-optimized
ebs_optimized = true

# Specify whether Nitro Enclaves will be enabled on the instance
enclave_options_enabled = true

instance_tags = {
  Environment = "Development"
  Owner      = "John Doe"
}

tags =  {
  "environment" = "dev",
  "project" = "dat"
}
