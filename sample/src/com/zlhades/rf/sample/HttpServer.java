package com.zlhades.rf.sample;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;

public class HttpServer {

    public static final int BACK_LOG = 10;
    private int port;
    private HttpHandler handler;

    public HttpServer(int port, HttpHandler handler) {

        this.port = port;
        this.handler = handler;
    }

    public void start() {

        try {
            int backLog = BACK_LOG;

            InetSocketAddress inetSock = new InetSocketAddress(port);
            com.sun.net.httpserver.HttpServer httpServer = com.sun.net.httpserver.HttpServer.create(inetSock, backLog);
            httpServer.createContext("/", handler);
            httpServer.setExecutor(null);
            httpServer.start();
            System.out.println("HttpServer Test Start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
