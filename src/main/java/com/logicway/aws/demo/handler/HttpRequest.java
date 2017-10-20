package com.logicway.aws.demo.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest<I> {
    private Map<String, String> queryStringParameters = new HashMap<>();
    private Map<String, String> pathParameters = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private I body;

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public I getBody() {
        return body;
    }

    public void setBody(String body) throws IOException {
        TypeReference ref = new TypeReference<I>() { };
        this.body = new ObjectMapper().readValue(body, ref);
    }
}
