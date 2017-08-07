package com.tcp.server.app;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class QueueProcessor{

    private static QueueProcessor processor = new QueueProcessor();
    private final static Logger LOGGER = Logger.getLogger(QueueProcessor.class.getName());

    private  QueueProcessor() {
    }

    public static QueueProcessor getProcessorInstance(){
        return processor;
    }

    public void absorbQueue(LinkedBlockingDeque<String>mainQueue){
        new Thread(){
            public void run(){
                while (true){
                    try {
                        String take = mainQueue.take();
                        LOGGER.info("PROCESSED AND RESPONDING ----> "+take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
