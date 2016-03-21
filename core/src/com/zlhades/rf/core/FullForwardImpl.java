package com.zlhades.rf.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FullForwardImpl implements Forwarder {
    private static final String POST_METHOD = "POST";
    private static FullForwardImpl instance = new FullForwardImpl();

    public static FullForwardImpl getInstance() {

        return instance;
    }

    private FullForwardImpl() {

    }

    public Result forward(HttpServletRequest request) {

        HttpURLConnection conn = null;
        String urlString = Utils.getInstance().buildURL(request);
        int responseCode = DEFAULT_HTTP_CODE;
        try {
            String method = request.getMethod();
            URL url = new URL(Utils.getInstance().buildURL(request));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            Utils.getInstance().setHeader(request, conn);

            boolean hasOutBody = (method.equals(POST_METHOD));
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(hasOutBody);

            conn.connect();

            Utils.getInstance().writePost(request, conn, hasOutBody);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("error in net work");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // pass
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return new Result(responseCode, urlString);
    }

}
