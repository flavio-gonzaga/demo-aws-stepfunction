package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.demo.aws.model.StepFunctionObj;
import com.github.demo.aws.model.StepResult;

public class ProcessLambda implements RequestHandler<StepFunctionObj, StepFunctionObj> {

    public StepFunctionObj handleRequest(StepFunctionObj input, Context context) {
        System.out.println("Input: " + input);

        StepResult result = new StepResult();
        result.setCode("200");
        result.setMessage("Process completed successfully.");
        input.setProcess(result);

        return input;

    }
}
