package com.zlhades.rf.core;

import java.security.InvalidParameterException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

public class ForwardManger {

    private static final int POOL_SIZE = 20;
    public static final String DEFAULT_PREFIX = "";

    public static String FORWARD_HTTP_PREFIX = DEFAULT_PREFIX;

    private ThreadPoolExecutor pool;
    private boolean simpleForward = false;

    public ForwardManger(String forwardHttpPrefix) {

        this(POOL_SIZE, forwardHttpPrefix, false);
    }

    public ForwardManger(int poolSize, String forwardHttpPrefix, boolean simpleForward) {

        forwardHttpPrefix = forwardHttpPrefix;
        this.simpleForward = simpleForward;
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    public Future<Result> forward(HttpServletRequest request) {

        if (FORWARD_HTTP_PREFIX == null)
            throw new InvalidParameterException("pls set forwardHttpPrefix");
        return pool.submit(buildForwardTask(request));
    }

    private ForwardTask buildForwardTask(HttpServletRequest request) {

        return new ForwardTask(request, simpleForward);
    }

}
