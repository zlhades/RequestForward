package com.zlhades.rf.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static final int BUFFER_SIZE = 2048;
    public static final String EMPTY_STRING = "";
    public static final String DELIMITER = ",";

    private static Utils instance = new Utils();

    public static Utils getInstance() {

        return instance;
    }


    public HashMap<String, String> getHeader(HttpServletRequest req) {

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

    public String arrayToString(String[] array) {

        if(array == null || array.length==0) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for(int i=1; i < array.length; i++) {
            sb.append(DELIMITER);
            sb.append(array[i]);
        }
        return sb.toString();
    }

}
