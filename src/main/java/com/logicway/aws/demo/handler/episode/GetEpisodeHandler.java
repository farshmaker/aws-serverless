package com.logicway.aws.demo.handler.episode;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.io.IOException;

public class GetEpisodeHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();

        try {
            Episode episode = mapper.readValue(awsProxyRequest.getBody(), Episode.class);
            episode = dynamoDBMapper.load(Episode.class, episode.getId());

            awsProxyResponse.setBody(GatewayResponse.createSuccess(mapper.writeValueAsString(episode)).toString());
            awsProxyResponse.setStatusCode(200);
        } catch (IOException e) {
            awsProxyResponse.setBody(GatewayResponse.createError("Error during getting").toString());
            awsProxyResponse.setStatusCode(500);
            e.printStackTrace();
        }

        return awsProxyResponse;
    }
}
