package com.zlhades.rf.core;

import java.util.concurrent.Callable;

public class ForwardTask implements Callable<Result> {


    private boolean simpleForward = false;
    private String urlString;
    private  RequestVO requestVO;

    public ForwardTask(boolean simpleForward, String urlString) {
        this.simpleForward = simpleForward;
        this.urlString = urlString;
    }

    @Override
    public Result call() throws Exception {


        if (simpleForward) {
            return SimpleForwarder.getInstance().forward(urlString);
        } else {
            return FullForwarder.getInstance().forward(requestVO);
        }
    }

    public void setRequestVO(RequestVO requestVO) {
        this.requestVO = requestVO;
    }
}
