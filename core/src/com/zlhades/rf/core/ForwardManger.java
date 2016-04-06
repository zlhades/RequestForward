package com.zlhades.rf.core;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

public class ForwardManger {

    private static final int POOL_SIZE = 10;
    public static final String DEFAULT_PREFIX = "";

    public static String FORWARD_HTTP_PREFIX = DEFAULT_PREFIX;

    private ExecutorService pool;
    private boolean simpleForward = false;

    public ForwardManger(String forwardHttpPrefix) {

        this(POOL_SIZE, forwardHttpPrefix, false);
    }

    public ForwardManger(int poolSize, String forwardHttpPrefix, boolean simpleForward) {

        FORWARD_HTTP_PREFIX = forwardHttpPrefix;
        this.simpleForward = simpleForward;
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public Future<Result> forward(HttpServletRequest request) {

        if (FORWARD_HTTP_PREFIX == null)
            throw new InvalidParameterException("pls set forwardHttpPrefix");
        return pool.submit(buildForwardTask(request));
    }

    private ForwardTask buildForwardTask(HttpServletRequest request) {

        Map<String, String> headers = Utils.getInstance().getHeader(request);
        Map<String, String> parameterMap = this.transform(request.getParameterMap());
        String urlString = Utils.getInstance().buildURL(request);
        ForwardTask task = new ForwardTask(simpleForward, urlString);
        if (simpleForward) {
            RequestVO requestVO = new RequestVO(urlString, request.getMethod(), headers, parameterMap);
            task.setRequestVO(requestVO);
        }
        return task;
    }

    private Map<String, String> transform(Map<String, String[]> parameterMap) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String[]> entity : parameterMap.entrySet()) {
            map.put(entity.getKey(), Utils.getInstance().arrayToString(entity.getValue()));
        }
        return map;
    }


}
