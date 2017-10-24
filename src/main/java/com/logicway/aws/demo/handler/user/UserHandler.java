package com.logicway.aws.demo.handler.user;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicway.aws.demo.entity.User;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.io.IOException;

public class UserHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    protected final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    protected final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        DynamoDBMapperConfig config = new DynamoDBMapperConfig.Builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .build();
        try {
            User user = mapper.readValue(awsProxyRequest.getBody(), User.class);
            if (awsProxyRequest.getPath().endsWith("register")) {
                dynamoDBMapper.save(user);
                awsProxyResponse.setBody(GatewayResponse.createSuccess(user.getId()).toString());

            } else if (awsProxyRequest.getPath().endsWith("get")) {
                dynamoDBMapper.load(User.class, user.getId());
                awsProxyResponse.setBody(GatewayResponse.createSuccess(mapper.writeValueAsString(user)).toString());

            } else {
                dynamoDBMapper.save(user, config);
                awsProxyResponse.setBody(GatewayResponse.createSuccess("ok").toString());
            }

            awsProxyResponse.setStatusCode(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return awsProxyResponse;
    }
}
