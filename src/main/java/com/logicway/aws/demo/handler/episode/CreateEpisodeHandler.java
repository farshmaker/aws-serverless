package com.logicway.aws.demo.handler.episode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.manager.DynamoDBManager;

public class CreateEpisodeHandler implements RequestHandler<Episode, Episode>{

    private final DynamoDBMapper dynamoDb = DynamoDBManager.mapper();

    @Override
    public Episode handleRequest(Episode episode, Context context) {
        dynamoDb.save(episode);
        return episode;
    }

    public static void main(String[] args) {
        Episode episode = new CreateEpisodeHandler().handleRequest(new Episode(), null);
        System.out.println(episode);
    }
}
