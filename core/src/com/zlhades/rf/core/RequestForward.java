package com.zlhades.rf.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestForward {
    private static final String CLIENT_BACKEND_HTTPS = "";
    private static RequestForward instance = new RequestForward();

    public static RequestForward getInstance() {
        return instance;
    }

    private RequestForward() {
    }

    private void forwardRequest(String method, HttpServletRequest req) {
        final boolean hasoutbody = (method.equals("POST"));

        try {
            final URL url = new URL(CLIENT_BACKEND_HTTPS  // no trailing slash
                    + req.getRequestURI()
                    + (req.getQueryString() != null ? "?" + req.getQueryString() : ""));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            conn.setDoOutput(hasoutbody);
            conn.connect();

            final byte[] buffer = new byte[2048];
            while (hasoutbody) {
                final int read = req.getInputStream().read(buffer);
                if (read <= 0) break;
                conn.getOutputStream().write(buffer, 0, read);
            }


        } catch (Exception e) {
            e.printStackTrace();
            // pass
        }
    }
}
