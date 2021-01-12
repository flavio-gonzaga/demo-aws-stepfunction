package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.demo.aws.model.StepFunctionObj;

public class ExceptionLambda implements RequestHandler<StepFunctionObj, StepFunctionObj> {

    public StepFunctionObj handleRequest(StepFunctionObj input, Context context) {
        System.out.println("Input: " + input);

        return input;
    }
}
