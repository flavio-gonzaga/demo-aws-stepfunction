package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.demo.aws.model.StepFunctionObj;
import com.github.demo.aws.model.StepResult;


public class ExceptionLambda implements RequestHandler<StepFunctionObj, StepFunctionObj> {
    public StepFunctionObj handleRequest(StepFunctionObj input, Context context) {
        return null;
    }

    public StepFunctionObj exception(StepFunctionObj input, Context context) throws Exception {
        System.out.println(String.format("process lambda"));

        input.setProcess(new StepResult());

        return input;
    }
}