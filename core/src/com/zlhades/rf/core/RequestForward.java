package com.zlhades.rf.core;

public class RequestForward {
    private static RequestForward instance = new RequestForward();

    public static RequestForward getInstance() {
        return instance;
    }

    private RequestForward() {
    }
}
