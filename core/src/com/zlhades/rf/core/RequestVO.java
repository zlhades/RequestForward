package com.zlhades.rf.core;

import java.util.HashMap;
import java.util.Map;

public class RequestVO {
    private String urlString;
    private String method;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> parameterMap = new HashMap<>();

    public RequestVO(String urlString, String method, Map<String, String> headers, Map<String, String> parameterMap) {

        this.urlString = urlString;
        this.method = method;
        this.headers = headers;
        this.parameterMap = parameterMap;
    }

    public String getUrlString() {

        return urlString;
    }

    public String getMethod() {

        return method;
    }

    public Map<String, String> getHeaders() {

        return headers;
    }

    public Map<String, String> getParameterMap() {

        return parameterMap;
    }
}
