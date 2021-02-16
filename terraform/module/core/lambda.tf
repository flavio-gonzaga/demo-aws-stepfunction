

########### Lambda ###########
resource "aws_lambda_function" "demo-validate" {
  function_name     = "${var.environment_prefix}-demo-validate"
  handler       = "com.github.demo.aws.lambda.ValidateLambda::handleRequest"
  runtime       = "java8"
  role          = "${var.lambda_role_arn}"
  filename          = "../../../target/${var.jar_file}"
  source_code_hash = "${base64sha256(file("../../../target/${var.jar_file}"))}"
  memory_size   = 512
  timeout       = 300
  environment {
    variables = {
      sns_topic = "${var.sns_topic}",
    }
  }
}

resource "aws_lambda_function" "demo-process" {
  function_name     = "${var.environment_prefix}-demo-process"
  handler       = "com.github.demo.aws.lambda.ProcessLambda::handleRequest"
  runtime       = "java8"
  role          = "${var.lambda_role_arn}"
  filename          = "../../../target/${var.jar_file}"
  source_code_hash = "${base64sha256(file("../../../target/${var.jar_file}"))}"
  memory_size   = 512
  timeout       = 300
  environment {
    variables = {
      sns_topic = "${var.sns_topic}",
    }
  }
}

resource "aws_lambda_function" "demo-exception" {
  function_name     = "${var.environment_prefix}-demo-exception"
  handler       = "com.github.demo.aws.lambda.ExceptionLambda::handleRequest"
  runtime       = "java8"
  role          = "${var.lambda_role_arn}"
  filename          = "../../../target/${var.jar_file}"
  source_code_hash = "${base64sha256(file("../../../target/${var.jar_file}"))}"
  memory_size   = 512
  timeout       = 300
  environment {
    variables = {
      sns_topic = "${var.sns_topic}",
    }
  }
}

resource "aws_lambda_function" "trigger-step-function" {
  function_name     = "${var.environment_prefix}-trigger-step-function"
  handler       = "com.github.demo.aws.lambda.TriggerStepFunction::handleRequest"
  runtime       = "java8"
  role          = "${var.lambda_role_arn}"
  filename          = "../../../target/${var.jar_file}"
  source_code_hash = "${base64sha256(file("../../../target/${var.jar_file}"))}"
  memory_size   = 512
  timeout       = 300
  environment {
    variables = {
      step_function_arn = "${aws_sfn_state_machine.main-workflow.arn}",
    }
  }
}

### Step function trigger from S3


resource "aws_lambda_permission" "allow_bucket" {
  statement_id  = "AllowExecutionFromS3Bucket"
  action        = "lambda:InvokeFunction"
  function_name = "${aws_lambda_function.trigger-step-function.arn}"
  principal     = "s3.amazonaws.com"
  source_arn    = "${var.s3_bucket_arn}"
}

resource "aws_s3_bucket_notification" "bucket_notification" {
  bucket = "${var.s3_bucket}"

  lambda_function {
    lambda_function_arn = "${aws_lambda_function.trigger-step-function.arn}"
    events              = ["s3:ObjectCreated:*"]
    filter_prefix       = "Input/"
    filter_suffix       = ".xml"
  }

}


