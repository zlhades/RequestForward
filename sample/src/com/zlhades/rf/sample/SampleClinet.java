package com.zlhades.rf.sample;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SampleClinet {
    public static void main(String[] args) {

        try {
            int backLog = 10;
            InetSocketAddress inetSock = new InetSocketAddress(8888);
            HttpServer httpServer = HttpServer.create(inetSock, backLog);
            httpServer.createContext("/", new HandlerTest());
            httpServer.setExecutor(null);
            httpServer.start();
            System.out.println("HttpServer Test Start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
