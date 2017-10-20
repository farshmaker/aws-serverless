package com.logicway.aws.demo.handler.episode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.handler.HttpRequest;
import com.logicway.aws.demo.handler.HttpRequestHandler;
import com.logicway.aws.demo.handler.HttpResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

public class CreateEpisodeHandler extends HttpRequestHandler<Episode, GatewayResponse> {

    private final DynamoDBMapper dynamoDb = DynamoDBManager.mapper();

    @Override
    public void handleRequest(HttpRequest<Episode> httpRequest, HttpResponse<GatewayResponse> httpResponse, Context context) throws JsonProcessingException {
        Episode episode = httpRequest.getBody();

        dynamoDb.save(episode);

        httpResponse.setBody(GatewayResponse.createSuccess(episode.getId()));
    }
}
