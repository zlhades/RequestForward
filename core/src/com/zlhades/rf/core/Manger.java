package com.zlhades.rf.core;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manger {

    private ExecutorService pool = Executors.newFixedThreadPool(20);

}
