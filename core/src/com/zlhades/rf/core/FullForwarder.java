package com.zlhades.rf.core;

public class FullForwarder {
    private static final String POST_METHOD = "POST";
    private static final int DEFAULT_HTTP_CODE = 0;
    private static FullForwarder instance = new FullForwarder();

    public static FullForwarder getInstance() {

        return instance;
    }

    private FullForwarder() {

    }

    public Result forward(RequestVO request) {

        //TODO use httpclient to do this job.
//        HttpURLConnection conn = null;
//        int responseCode = DEFAULT_HTTP_CODE;
//        try {
//            String method = request.getMethod();
//            URL url = new URL(request.getUrlString());
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod(method);
//
//            Utils.getInstance().setHeader(request, conn);
//
//            boolean hasOutBody = (method.equals(POST_METHOD));
//            conn.setUseCaches(false);
//            conn.setDoInput(true);
//            conn.setDoOutput(hasOutBody);
//
//            conn.connect();
//
//            Utils.getInstance().writePost(request, conn, hasOutBody);
//
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                System.out.println("error in net work");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // pass
//        } finally {
//            if (conn != null)
//                conn.disconnect();
//        }
//        return new Result(responseCode, request.getUrlString());
        return null;
    }

}
