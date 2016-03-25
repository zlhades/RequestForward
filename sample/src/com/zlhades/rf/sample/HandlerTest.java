package com.zlhades.rf.sample;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HandlerTest implements HttpHandler {
    private boolean isNeedForward = false;

//    private ForwardManger forwardManger = new
    public HandlerTest() {

    }

    public HandlerTest(boolean isNeedForward) {

        this.isNeedForward = isNeedForward;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println(Thread.currentThread().getName() + " Get request :" + httpExchange.getRequestURI());
        if(isNeedForward) {

        }
        OutputStream outputStream = null;

        try {
            httpExchange.sendResponseHeaders(200, 0L);
            outputStream = httpExchange.getResponseBody();
            outputStream.write(new byte[0]);
            outputStream.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            this.closeOutputStream(outputStream);
        }
    }

    private void closeOutputStream(OutputStream os) throws IOException {

        if (os != null) {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
