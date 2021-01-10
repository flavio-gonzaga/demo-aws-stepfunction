
resource "aws_sfn_state_machine" "main-workflow" {
  name     = "${var.environment_prefix}-main-workflow"
  role_arn = "${var.lambda_role_arn}"
  definition = <<EOF

{
  "StartAt": "Validate",
  "States": {
    "Validate": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.demo-validate.arn}",
      "Next": "Process",
      "TimeoutSeconds": 120,
      "Catch" : [{
        "ErrorEquals" : ["States.ALL"],
        "ResultPath": "$.error",
        "Next" : "Exception"
      }]
    },
    "Process": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.demo-process.arn}",
      "Next": "Succeeded",
      "TimeoutSeconds": 120,
      "Catch" : [{
        "ErrorEquals" : ["States.ALL"],
        "ResultPath": "$.error",
        "Next" : "Exception"
      }]
    },
    "Exception" : {
      "Type": "Task",
      "Resource": "${aws_lambda_function.demo-exception.arn}",
      "ResultPath": "$.error.exception_handled",
      "Next" : "Failed"
    },
    "Succeeded" : {
      "End" : true,
      "Type" : "Pass"
    },
    "Failed": {
      "Type": "Fail"
    }
  }
}

EOF

}

########### OUTPUT ###########
output "step-function-main-workflow" {value = "${aws_sfn_state_machine.main-workflow.name}"}
