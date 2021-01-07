package com.github.demo.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.util.StringUtils;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class ValidateLambda implements RequestHandler<Object, String> {
    public String handleRequest(Object input, Context context) {
        return null;
    }

    public String readInput(Object input, Context context) throws Exception {

        String bucketName = JsonPath.read(input, "$.event.Records[0].s3.bucket.name");
        String keyName = JsonPath.read(input, "$.event.Records[0].s3.object.key");
        System.out.println(String.format("Bucket name:%s Key:%s ", bucketName));

        return "All good";
    }
}