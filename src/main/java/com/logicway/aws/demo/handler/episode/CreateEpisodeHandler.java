package com.logicway.aws.demo.handler.episode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

public class CreateEpisodeHandler implements RequestHandler<Episode, GatewayResponse> {

    private final DynamoDBMapper dynamoDb = DynamoDBManager.mapper();

    @Override
    public GatewayResponse handleRequest(Episode episode, Context context) {
        dynamoDb.save(episode);
        return GatewayResponse.createSuccess(episode.getId());
    }
}
