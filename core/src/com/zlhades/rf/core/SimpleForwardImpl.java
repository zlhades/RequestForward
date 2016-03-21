package com.zlhades.rf.core;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class SimpleForwardImpl implements Forwarder {

    private static SimpleForwardImpl instance = new SimpleForwardImpl();

    public static SimpleForwardImpl getInstance() {

        return instance;
    }

    private SimpleForwardImpl() {

    }

    public Result forward(HttpServletRequest request) {

        HttpURLConnection conn = null;
        String urlString = Utils.getInstance().buildURL(request);
        int responseCode = DEFAULT_HTTP_CODE;
        try {

            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(request.getMethod());

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
