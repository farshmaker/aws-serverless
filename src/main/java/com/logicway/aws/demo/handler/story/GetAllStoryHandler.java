package com.logicway.aws.demo.handler.story;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicway.aws.demo.entity.Story;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.util.List;

public class GetAllStoryHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Story> stories = dynamoDBMapper.scan(Story.class, scanExpression);

        try {
            awsProxyResponse.setBody(GatewayResponse.createSuccess(mapper.writeValueAsString(stories)).toString());
            awsProxyResponse.setStatusCode(200);
        } catch (JsonProcessingException e) {
            awsProxyResponse.setBody(GatewayResponse.createError("Error during getting all stories").toString());
            awsProxyResponse.setStatusCode(500);
        }

        return awsProxyResponse;

    }
}
