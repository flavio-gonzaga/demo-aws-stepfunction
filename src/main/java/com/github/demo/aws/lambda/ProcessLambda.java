package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.demo.aws.model.StepFunctionObj;
import com.github.demo.aws.model.StepResult;

public class ProcessLambda implements RequestHandler<StepFunctionObj, StepFunctionObj> {
    public StepFunctionObj handleRequest(StepFunctionObj input, Context context) {
        return null;
    }

    public StepFunctionObj process(StepFunctionObj input, Context context) throws Exception {

        System.out.println(String.format("Bucket name:%s Key:%s ", input.getBucket(), input.getKey()));

        input.setProcess(new StepResult());
        input.getProcess().setCode("200");
        input.getProcess().setMessage("Process complete");

        return input;
    }
}