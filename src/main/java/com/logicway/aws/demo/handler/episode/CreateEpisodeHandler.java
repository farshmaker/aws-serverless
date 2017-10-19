package com.logicway.aws.demo.handler.episode;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.logicway.aws.demo.DefaultDynamoDB;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.util.Date;

public class CreateEpisodeHandler implements RequestHandler<Episode, Episode>{

//    private static DynamoDBMapper dynamoDB = DefaultDynamoDB.newMapperInstance();

    @Override
    public Episode handleRequest(Episode episode, Context context) {

        DynamoDBMapper dynamoDB = DynamoDBManager.mapper();
//        AmazonDynamoDBClientBuilder.standard()
//                .withRegion(Regions.US_EAST_1)
//                .build();
        dynamoDB.save(episode);

        System.out.println(episode);

        return episode;
    }

    public static void main(String[] args) {
        Episode episode = new Episode();
        episode.setNumber(100);
        episode.setAvailableAt(new Date());
        new CreateEpisodeHandler().handleRequest(episode, null);
    }
}
