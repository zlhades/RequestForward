package com.zlhades.rf.core;

public class Result {
    public int status;
    public String url;

    public Result(int status, String url) {
        this.status = status;
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
