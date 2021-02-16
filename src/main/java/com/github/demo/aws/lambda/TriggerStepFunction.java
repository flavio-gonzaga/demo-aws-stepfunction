package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.google.gson.Gson;

import java.util.HashMap;

public class TriggerStepFunction implements RequestHandler<S3Event, Void> {

    private static final String arn = System.getenv("step_function_arn");
    Gson gson = new Gson();

    @Override
    public Void handleRequest(S3Event event, Context context) {

        System.out.println("S3Event Received: " + gson.toJson(event));

        HashMap<String, Object> myData = new HashMap<>();
        myData.put("input", "s3");
        myData.put("action", "process");

        HashMap<String, Object> executionInput = new HashMap<>();
        executionInput.put("mydata", myData);
        executionInput.put("event", event);
        String executionInputJson = gson.toJson(executionInput);

        StartExecutionRequest request = new StartExecutionRequest();
        request.setStateMachineArn(arn);
        request.setInput(executionInputJson);

        StartExecutionResult result = AWSStepFunctionsClientBuilder.defaultClient().startExecution(request);
        System.out.println("Triggered: " + arn + " with input: " + executionInputJson);

        return null;
    }

}
