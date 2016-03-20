package com.zlhades.rf.sample;

public class SampleClient {
    public static void main(String[] args) {

        HttpServer server = new HttpServer(8888, new HandlerTest());
        server.start();
    }

}
