based on the below code instances were created successfullhy without names provide the instance names as dat_one and dat_two like that provide the updated accordingly


tf/modules/aws-rds/main.tf

resource "aws_db_subnet_group" "aws_rds_subnet_group" {
  name       = "dat_subnet"
  subnet_ids = ["subnet-0cca75192de9fbbc8", "subnet-0db3fc4b1a19c3d00"]
}


resource "aws_db_instance" "rds_instances" {
  for_each = var.rds_instances

  allocated_storage    = each.value.allocated_storage
  storage_type         = each.value.storage_type
  engine               = "postgres"
  instance_class       = each.value.instance_class
  name                 = each.key
  username             = each.value.username
  password             = each.value.password
  skip_final_snapshot  = true
  vpc_security_group_ids = [each.value.security_group_ids]
  db_subnet_group_name = aws_db_subnet_group.aws_rds_subnet_group.name
  tags = each.value.tags
}


tf/modules/aws-rds/variables.tf

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


tf/modules/aws-rds/varabiles.tfvars

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
