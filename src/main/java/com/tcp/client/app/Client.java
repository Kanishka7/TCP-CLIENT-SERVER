package com.tcp.client.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class Client implements Runnable{
    private volatile  int runner = 0;
    private final int PORT=3000;
    private int requestLimit = 0;
    private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

    public Client(int requestLimit){
        this.requestLimit = requestLimit;
    }

    public void run(){
        while(true){
            try {
                synchronized (this){
                    if (runner < this.requestLimit) {
                        Socket clientSocket = new Socket("localhost", PORT);
                        DataOutputStream stream = new DataOutputStream(clientSocket.getOutputStream());
                        stream.writeBytes("CLIENT HELLO -------->" + (++runner));
                        clientSocket.close();
                    } else {
                        LOGGER.info("MAX CLIENT REQUEST LIMIT REACHED CLIENT SHUTTING DOWN <------->");
                        System.exit(0);
                    }
                }
            }catch (IOException e){
                LOGGER.warning("UNABLE TO OPEN PORT 3000 ----ALREADY IN USE");
            }
        }
    }
}
