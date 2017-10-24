package com.logicway.aws.demo.handler.story;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicway.aws.demo.entity.Story;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.io.IOException;

public class RemoveStoryHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();

        try {
            Story story = mapper.readValue(awsProxyRequest.getBody(), Story.class);

            dynamoDBMapper.delete(story);
            awsProxyResponse.setBody(GatewayResponse.createSuccess("ok").toString());
            awsProxyResponse.setStatusCode(200);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return awsProxyResponse;
    }
}
