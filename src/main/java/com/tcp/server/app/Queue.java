package com.tcp.server.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Queue {

    private static LinkedBlockingDeque<String> blockingQueue = new LinkedBlockingDeque<>();
    private static Queue sharedQueue = new Queue();
    private Queue(){
    }
    public static Queue sharedQueue(){
        return sharedQueue;
    }
    public static LinkedBlockingDeque<String> getQueue(){
        return blockingQueue;
    }
}
