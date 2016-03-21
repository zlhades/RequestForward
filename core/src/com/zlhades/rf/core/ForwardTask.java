package com.zlhades.rf.core;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

public class ForwardTask implements Callable<Result> {

    private HttpServletRequest request;
    private boolean simpleForward = false;

    public ForwardTask(HttpServletRequest request, boolean simpleForward) {

        this.request = request;
        this.simpleForward = simpleForward;
    }

    @Override
    public Result call() throws Exception {

        if (simpleForward) {
            return SimpleForwardImpl.getInstance().forward(request);
        } else {
            return FullForwardImpl.getInstance().forward(request);
        }
    }
}
