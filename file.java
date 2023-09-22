module "ec2_instance" {
  source  = "./modules/aws-ec2"

  #for_each = toset(["one", "two", "three"])
  name = "dat-${each.key}"
  for_each = {
    one   = {
      instance_type        = "t3.micro"
      subnet_id            = "subnet-2529cb04"
      vpc_security_group_ids = ["sg-0684776069a694f30"]
      ebs_block_device     = [
        { device_name = "/dev/sdb", volume_type = "gp2", volume_size = 35, delete_on_termination = true }
      ]
    }
    two   = {
      instance_type        = "t2.micro"
      subnet_id            = "subnet-2529cb04"
      vpc_security_group_ids = ["sg-b1d6ca9a"]
      ebs_block_device     = [
        { device_name = "/dev/sdb", volume_type = "gp2", volume_size = 40, delete_on_termination = true }
      ]
    }
  }
tags =  {
  "Tag2" = "Tag2_Value_Here",
  "Tag3" = "Tag3_Value_Here"
}

  instance_type = each.value.instance_type
  subnet_id = each.value.subnet_id
  vpc_security_group_ids = each.value.vpc_security_group_ids
  ebs_block_device = each.value.ebs_block_device
}
