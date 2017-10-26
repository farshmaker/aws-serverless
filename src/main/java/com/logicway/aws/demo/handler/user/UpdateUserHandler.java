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

public class UpdateUserHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .build();

        try {
            User user = mapper.readValue(awsProxyRequest.getBody(), User.class);

            dynamoDBMapper.save(user, config);
            awsProxyResponse.setBody(GatewayResponse.createSuccess("ok").toString());
            awsProxyResponse.setStatusCode(200);
        } catch (IOException e) {
            awsProxyResponse.setBody(GatewayResponse.createError("Error during update user").toString());
            awsProxyResponse.setStatusCode(500);
        }


        return awsProxyResponse;
    }
}
