package com.zlhades.rf.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class FullForwarder {
    private static final String POST_METHOD = "POST";
    private static final String GET_METHOD = "GET";
    public static final String UTF_8 = "UTF-8";
    private static FullForwarder instance = new FullForwarder();

    public static FullForwarder getInstance() {

        return instance;
    }

    private FullForwarder() {

    }

    public Result forward(RequestVO request) {

        CloseableHttpClient httpclient = null;
        int responseCode = 0;
        try {
            httpclient = HttpClients.createDefault();
            HttpRequestBase method = null;
            if (POST_METHOD.equals(request.getMethod())) {
                method = buildPost(request);
            } else if (GET_METHOD.equals(request.getMethod())) {
                method = buildGet(request);
            }
            HttpResponse httpResponse = httpclient.execute(method);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            //            InputStream inStream = httpResponse.getEntity().getContent();
            //            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            //            StringBuilder strber = new StringBuilder();
            //
            //            String line = null;
            //            while ((line = reader.readLine()) != null)
            //                strber.append(line + "\n");
            //            inStream.close();
            //            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //                HttpEntity entity = httpResponse.getEntity();
            //                String charset = EntityUtils.getContentCharSet(entity);
            //                //  response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
            //            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new Result(responseCode, request.getUrlString());
    }

    private HttpRequestBase buildPost(RequestVO request) throws UnsupportedEncodingException {

        HttpPost post = new HttpPost(request.getMethod());
        post.setEntity(buildHttpEntity(request.getParameterMap()));
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            post.addHeader(entry.getKey(), entry.getValue());
        }
        return post;
    }

    private HttpRequestBase buildGet(RequestVO request) throws UnsupportedEncodingException {

        HttpGet get = new HttpGet(request.getMethod());
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            get.addHeader(entry.getKey(), entry.getValue());
        }
        return get;
    }

    private HttpEntity buildHttpEntity(Map<String, String> parameterMap) throws UnsupportedEncodingException {

        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return new UrlEncodedFormEntity(list, UTF_8);
    }

}
