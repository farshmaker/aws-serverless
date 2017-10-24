package com.logicway.aws.demo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable(tableName="Users")
public class User {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    private String scenarioId;
    private String testData;
    private Date register;
    private Date lastActivity;
    private Date subscription;
    private boolean isFree;
    private String appleToken;
    private String curEpisode;
    private Integer curMessage;
    private String locale;
    private Date productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Date getSubscription() {
        return subscription;
    }

    public void setSubscription(Date subscription) {
        this.subscription = subscription;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public String getAppleToken() {
        return appleToken;
    }

    public void setAppleToken(String appleToken) {
        this.appleToken = appleToken;
    }

    public String getCurEpisode() {
        return curEpisode;
    }

    public void setCurEpisode(String curEpisode) {
        this.curEpisode = curEpisode;
    }

    public Integer getCurMessage() {
        return curMessage;
    }

    public void setCurMessage(Integer curMessage) {
        this.curMessage = curMessage;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getProductId() {
        return productId;
    }

    public void setProductId(Date productId) {
        this.productId = productId;
    }
}
