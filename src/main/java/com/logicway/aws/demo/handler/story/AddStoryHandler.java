package com.logicway.aws.demo.handler.story;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicway.aws.demo.entity.Episode;
import com.logicway.aws.demo.entity.Story;
import com.logicway.aws.demo.gateway.GatewayResponse;
import com.logicway.aws.demo.manager.DynamoDBManager;

import java.io.IOException;

public class AddStoryHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private final DynamoDBMapper dynamoDBMapper = DynamoDBManager.mapper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();

        try {
            Story story = mapper.readValue(awsProxyRequest.getBody(), Story.class);

            dynamoDBMapper.save(story);
            awsProxyResponse.setBody(GatewayResponse.createSuccess(story.getId()).toString());
            awsProxyResponse.setStatusCode(200);
        } catch (IOException e) {
            awsProxyResponse.setBody(GatewayResponse.createError("Error during addition story").toString());
            awsProxyResponse.setStatusCode(500);
        }

        return awsProxyResponse;
    }

    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                "\"storyId\":\"7ed6222d-4d24-44b8-8be0-19104d25e76d\",\n" +
                "\"number\" :0,\n" +
                "\"description\" : \"description\",\n" +
                "\"textjson\" : {\n" +
                "        \"people\": [\n" +
                "            {\n" +
                "                \"bubbleColor\": \"ebeff4\",\n" +
                "                \"textColor\": \"сс000000\",\n" +
                "                \"id\": 400,\n" +
                "                \"name\": \"Lindsay\",\n" +
                "                \"nameColor\": \"25aef4\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"bubbleColor\": \"f6ecec\",\n" +
                "                \"textColor\": \"сс000000\",\n" +
                "                \"id\": 401,\n" +
                "                \"name\": \"Becca\",\n" +
                "                \"nameColor\": \"ea5aa3\"\n" +
                "            }\n" +
                "        ],\n" +
                "         \"messages\": [\n" +
                "            {\n" +
                "                \"personId\": 400,\n" +
                "                \"body\": \"Are you guys still coming over my house before the game?\",\n" +
                "                \"typing\": false\n" +
                "            },\n" +
                "            {\n" +
                "                \"personId\": 401,\n" +
                "                \"body\": \"Planning on it.\",\n" +
                "                \"typing\": false\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();

        Episode story = mapper.readValue(json, Episode.class);
//        Map story = mapper.readValue(json, Map.class);
        System.out.println(story.getTextjson());


    }
}
