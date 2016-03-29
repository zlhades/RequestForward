package com.zlhades.rf.core;

import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleForwarder {

    private static final String GET_METHOD = "GET";
    private static final int DEFAULT_HTTP_CODE = 0;
    private static SimpleForwarder instance = new SimpleForwarder();

    public static SimpleForwarder getInstance() {

        return instance;
    }

    private SimpleForwarder() {

    }

    public Result forward(String urlString) {

        HttpURLConnection conn = null;
        int responseCode = DEFAULT_HTTP_CODE;
        try {

            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET_METHOD);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            return new Result(conn.getResponseCode(), urlString);
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
