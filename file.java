main.tf:

provider "aws" {
  region = "us-east-1" # Set your desired AWS region
}

resource "aws_db_instance" "rds_instances" {
  for_each = var.rds_instances

  allocated_storage    = each.value.allocated_storage
  storage_type         = each.value.storage_type
  engine               = "postgres"
  engine_version       = "12.6"
  instance_class       = each.value.instance_class
  name                 = each.key
  username             = each.value.username
  password             = each.value.password
  skip_final_snapshot  = true
  vpc_security_group_ids = [each.value.security_group_ids]
  tags = each.value.tags
}


variables.tf:

variable "rds_instances" {
  type = map(object({
    allocated_storage    = number
    storage_type         = string
    instance_class       = string
    username             = string
    password             = string
    security_group_ids   = string
    tags                 = map(string)
  }))
  default = {
    example_instance_1 = {
      allocated_storage    = 20
      storage_type         = "gp2"
      instance_class       = "db.t2.micro"
      username             = "exampleuser1"
      password             = "examplepassword1"
      security_group_ids   = "sg-0123456789abcdef0"
      tags = {
        Name = "ExampleInstance1"
      }
    }
    example_instance_2 = {
      allocated_storage    = 50
      storage_type         = "gp2"
      instance_class       = "db.t2.small"
      username             = "exampleuser2"
      password             = "examplepassword2"
      security_group_ids   = "sg-0123456789abcdef1"
      tags = {
        Name = "ExampleInstance2"
      }
    }
    # Add more RDS instance configurations as needed
  }
}

variables.tfvars:
rds_instances = {
  example_instance_1 = {
    allocated_storage    = 20
    storage_type         = "gp2"
    instance_class       = "db.t2.micro"
    username             = "exampleuser1"
    password             = "examplepassword1"
    security_group_ids   = "sg-0123456789abcdef0"
    tags = {
      Name = "ExampleInstance1"
    }
  }
  example_instance_2 = {
    allocated_storage    = 50
    storage_type         = "gp2"
    instance_class       = "db.t2.small"
    username             = "exampleuser2"
    password             = "examplepassword2"
    security_group_ids   = "sg-0123456789abcdef1"
    tags = {
      Name = "ExampleInstance2"
    }
  }
  # Add more RDS instance configurations as needed
}

