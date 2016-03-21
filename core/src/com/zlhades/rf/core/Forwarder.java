package com.zlhades.rf.core;

import javax.servlet.http.HttpServletRequest;

public interface Forwarder {

    int DEFAULT_HTTP_CODE = 0;

    String POST_METHOD = "POST";

    Result forward(HttpServletRequest req);


}
