package com.tcp.client.app;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        LOGGER.info("CLIENT MAIN THREAD STARTED <------------>");
        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(args[0]));
        Client client = new Client(Integer.parseInt(args[0]));
        executor.execute(client);
    }
}
