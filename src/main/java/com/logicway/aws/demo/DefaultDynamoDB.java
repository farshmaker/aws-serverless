package com.logicway.aws.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DefaultDynamoDB {
    protected static final String REGION = Regions.US_EAST_1.name();

    public static AmazonDynamoDB newInstance(){
//        return  AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", REGION))
//                .build();

        return  AmazonDynamoDBClientBuilder.standard()
                .withRegion(REGION)
                .build();
    }

    public static void main(String[] args) {
        AmazonDynamoDB amazonDynamoDB = newInstance();
        System.out.println(amazonDynamoDB.listTables());
    }
}
