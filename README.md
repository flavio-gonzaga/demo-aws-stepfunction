# demo-aws-stepfunction
Demo AWS Step Function and Lambdas using Terraform

### Configure AWS CLI

Configure authentication on CLI. Enter access key, secret key and AWS region. For region use: us-east-1
````bash
$ aws configure --profile dev-01
````

### Terraform Tasks
First update the terraform variables accordingly at: terraform/variables.tf

CD to terraform folder
````bash
$ cd terraform/environment/dev-01
````

Init terraform
````bash
$ terraform init
````

Apply changes/deploy to AWS
````bash
$ terraform apply
````

Destroy changes to AWS
````bash
$ terraform destroy
````

### Example JSON for testing Step Function

CD to terraform folder
````bash
{
	"event": {
		"Records": [
			{
				"s3": {
					"bucket": {
						"name": "flavio-demo01"
					},
					"object": {
						"key": "test-files/test.json"
					}
				}
			}
		]
	}
}````
