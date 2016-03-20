package com.zlhades.rf.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class Forwarder {
    public static final int BUFFER_SIZE = 2048;
    private static String FORWARD_HTTP_PREFIX = "http://127.0.0.1:8888";
    private static Forwarder instance = new Forwarder();

    public static Forwarder getInstance() {

        return instance;
    }

    private Forwarder() {

    }

    public void forward(HttpServletRequest req) {

        HttpURLConnection conn = null;
        final String method = req.getMethod();
        final boolean hasOutBody = (method.equals("POST"));

        try {
            final URL url = new URL(buildURL(req));
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

            final byte[] buffer = new byte[BUFFER_SIZE];
            while (hasOutBody) {
                final int read = req.getInputStream().read(buffer);
                if (read <= 0)
                    break;
                conn.getOutputStream().write(buffer, 0, read);
            }

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
    }

    private String buildURL(HttpServletRequest req) {

        return FORWARD_HTTP_PREFIX + req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
    }

}
