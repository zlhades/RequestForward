package com.zlhades.rf.sample;


public class SampleServer {
    public static void main(String[] args) {

        HttpServer httpServer = new HttpServer(8080, new HandlerTest());
    }
}
