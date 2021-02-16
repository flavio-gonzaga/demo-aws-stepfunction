provider "aws" {
  region = "${var.region}"
  profile = "dev-01"
}

// Main configuration
variable "region"               {default = "us-east-2"}
variable "jar_file"             {default = "demo-aws-stepfunction-1.0.0-SNAPSHOT.jar"}
variable "environment_prefix"   {default = "dev-01"}

// AWS pre-existing resources
variable "lambda_role_arn"      {default = "arn:aws:iam::473543219091:role/lambda-role"}
variable "s3_bucket"            {default = "flavio-bucket-01"}
variable "s3_bucket_arn"        {default = "arn:aws:s3:::flavio-bucket-01"}


module "core" {
  source                    = "../../module/core"
  region                    = "${var.region}"
  jar_file                  = "${var.jar_file}"
  sns_topic                 = ""
  environment_prefix        = "${var.environment_prefix}"
  lambda_role_arn           = "${var.lambda_role_arn}"
  s3_bucket                 = "${var.s3_bucket}"
  s3_bucket_arn             = "${var.s3_bucket_arn}"
}




