package com.zlhades.rf.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static final int BUFFER_SIZE = 2048;

    private static Utils instance = new Utils();

    public static Utils getInstance() {

        return instance;
    }

    public void writePost(HttpServletRequest req, HttpURLConnection conn, boolean hasOutBody) throws IOException {

        final byte[] buffer = new byte[BUFFER_SIZE];
        while (hasOutBody) {
            final int read = req.getInputStream().read(buffer);
            if (read <= 0)
                break;
            conn.getOutputStream().write(buffer, 0, read);
        }
    }

    public void setHeader(HttpServletRequest req, HttpURLConnection conn) {

        HashMap<String, String> headerValue = getHeader(req);
        for (Map.Entry<String, String> entity : headerValue.entrySet()) {
            conn.addRequestProperty(entity.getKey(), entity.getValue());
        }
    }

    private HashMap<String, String> getHeader(HttpServletRequest req) {

        HashMap<String, String> headerValue = new HashMap<>();
        final Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            final String header = headers.nextElement();
            final Enumeration<String> values = req.getHeaders(header);
            while (values.hasMoreElements()) {
                final String value = values.nextElement();
                headerValue.put(header, value);
            }
        }
        return headerValue;
    }

    String buildURL(HttpServletRequest req) {

        return ForwardManger.FORWARD_HTTP_PREFIX + req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
    }

}
