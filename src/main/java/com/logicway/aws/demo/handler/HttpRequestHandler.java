package com.logicway.aws.demo.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class HttpRequestHandler<I, O> implements RequestStreamHandler{

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        context.getLogger().log("Request: "+mapper.readValue(input, String.class));
        TypeReference inputRef = new TypeReference<HttpRequest<I>>() { };
        HttpRequest<I> httpRequest = mapper.readValue(input, inputRef);
        HttpResponse<O> httpResponse = new HttpResponse<>();
        handleRequest(httpRequest, httpResponse, context);
        context.getLogger().log("Response: "+mapper.writeValueAsString(httpResponse));
        mapper.writeValue(output, httpResponse);
    }

    public abstract void handleRequest(HttpRequest<I> httpRequest, HttpResponse<O> httpResponse, Context context) throws JsonProcessingException;
}
