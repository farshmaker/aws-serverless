package com.logicway.aws.demo.example.movie;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.logicway.aws.demo.DefaultDynamoDB;

import java.util.HashMap;
import java.util.Iterator;

public class MoviesQuery {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = DefaultDynamoDB.newInstance();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Movies");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "year");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", 1985);

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {
            System.out.println("Movies from 1985");
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                System.out.println(item.getNumber("year") + ": " + item.getString("title"));
            }

        }
        catch (Exception e) {
            System.err.println("Unable to query movies from 1985");
            System.err.println(e.getMessage());
        }

        valueMap.put(":yyyy", 1992);
        valueMap.put(":letter1", "A");
        valueMap.put(":letter2", "L");

        querySpec.withProjectionExpression("#yr, title, info.genres, info.actors[0]")
                .withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2").withNameMap(nameMap)
                .withValueMap(valueMap);

        try {
            System.out.println("Movies from 1992 - titles A-L, with genres and lead actor");
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                System.out.println(item.getNumber("year") + ": " + item.getString("title") + " " + item.getMap("info"));
            }

        }
        catch (Exception e) {
            System.err.println("Unable to query movies from 1992:");
            System.err.println(e.getMessage());
        }
    }
}