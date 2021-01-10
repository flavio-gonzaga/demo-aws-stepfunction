package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.github.demo.aws.model.StepFunctionObj;
import com.github.demo.aws.model.StepResult;
import com.jayway.jsonpath.JsonPath;

public class ValidateLambda implements RequestHandler<Object, StepFunctionObj> {
    public StepFunctionObj handleRequest(Object input, Context context) {
        return null;
    }

    public StepFunctionObj validate(Object input, Context context) throws Exception {

        System.out.println(String.format("Input:%s ", input));

        String bucket = JsonPath.read(input, "$.event.Records[0].s3.bucket.name");
        String key = JsonPath.read(input, "$.event.Records[0].s3.object.key");

        StepFunctionObj stepFunctionObj = new StepFunctionObj();

        // Validate
        if (bucket != null && key != null && bucket.length() > 0 && key.length() > 0) {
            stepFunctionObj.setBucket(bucket);
            stepFunctionObj.setKey(key);
        } else {
            throw new RuntimeException("Invalid input: " + stepFunctionObj);
        }

        stepFunctionObj.setValidate(new StepResult());
        stepFunctionObj.getValidate().setCode("200");
        stepFunctionObj.getValidate().setMessage("Validation completed successfully");

        System.out.println("Input: " + stepFunctionObj);

        return stepFunctionObj;
    }
}
