package com.zlhades.rf.core;

import java.security.InvalidParameterException;
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

        return new ForwardTask(request, simpleForward);
    }

}
