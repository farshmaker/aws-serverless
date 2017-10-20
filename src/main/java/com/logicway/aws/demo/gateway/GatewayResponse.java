package com.logicway.aws.demo.gateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * POJO containing response object for API Gateway.
 */
public class GatewayResponse {

    private final Boolean error;
    private final Object data;
    private final String message;

    protected GatewayResponse(boolean isError, Object data, String errorMessage){
        this.error = isError;
        this.data = data;
        this.message = errorMessage;
    }

    public static GatewayResponse createSuccess(Object data) {
        return new GatewayResponse(false, data, "");
    }

    public static GatewayResponse createError(String errorMessage) {
        return new GatewayResponse(true, "", errorMessage);
    }

    public Boolean getError() {
        return error;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
