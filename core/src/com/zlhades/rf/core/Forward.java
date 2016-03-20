package com.zlhades.rf.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class Forward {
    private static final String CLIENT_BACKEND_HTTPS = "http://127.0.0.1:8888";
    private static Forward instance = new Forward();

    public static Forward getInstance() {

        return instance;
    }

    private Forward() {

    }


    public void forwardRequest(HttpServletRequest req) {
        HttpURLConnection conn = null;
        final String method = req.getMethod();
        final boolean hasOutBody = (method.equals("POST"));

        try {
            final URL url = new URL(CLIENT_BACKEND_HTTPS // no trailing slash
                    + req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : ""));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            final Enumeration<String> headers = req.getHeaderNames();
            while (headers.hasMoreElements()) {
                final String header = headers.nextElement();
                final Enumeration<String> values = req.getHeaders(header);
                while (values.hasMoreElements()) {
                    final String value = values.nextElement();
                    conn.addRequestProperty(header, value);
                }
            }

            //conn.setFollowRedirects(false);  // throws AccessDenied exception
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(hasOutBody);
            conn.connect();

            final byte[] buffer = new byte[2048];
            while (hasOutBody) {
                final int read = req.getInputStream().read(buffer);
                if (read <= 0)
                    break;
                conn.getOutputStream().write(buffer, 0, read);
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("erro in net work");

            }

        } catch (Exception e) {
            e.printStackTrace();
            // pass
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
