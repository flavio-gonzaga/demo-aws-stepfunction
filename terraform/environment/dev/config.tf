provider "aws" {
  region = "${var.region}"
  profile = "dev-flavio"
}

//terraform {
//  backend "s3" {
//    bucket = "flavio-dev-test"
//    key    = "states/demo.tfstate"
//    region = "us-east-2"
//    profile = "dev-flavio"
//  }
//}

// Main configuration
variable "region"               {default = "us-east-2"}
variable "jar_file"             {default = "demo-aws-stepfunction-1.0.0-SNAPSHOT.jar"}
variable "environment_prefix"   {default = "dev01"}

// AWS pre-existing resources
variable "lambda_role_arn"      {default = "arn:aws:iam::473543219091:role/lambda-role-01"}

module "core" {
  source                    = "../../module/core"
  region                    = "${var.region}"
  jar_file                  = "${var.jar_file}"
  sns_topic                 = "${var.jar_file}"
  environment_prefix        = "${var.environment_prefix}"
  lambda_role_arn           = "${var.lambda_role_arn}"
}




