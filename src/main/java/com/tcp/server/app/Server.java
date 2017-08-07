package com.tcp.server.app;
import java.io.*;
import java.net.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class Server extends Thread{

    private LinkedBlockingDeque<String> theQueue = null;
    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private  final int PORT = 3000;
    private final int SLEEP =3000;

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(PORT);
            LOGGER.info("Server socket listening <-------> on port 3000");
            while (true) {
                Socket connectionSocket = socket.accept();
                BufferedReader messageFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String clientSentence = messageFromClient.readLine();
                theQueue =  Queue.sharedQueue().getQueue();
                theQueue.add(clientSentence);
                QueueProcessor.getProcessorInstance().absorbQueue(theQueue);
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            LOGGER.warning("PORT 3000 UTILIZED UNABLE TO OPEN SOCKET ???????????????? PLEASE FREE THE PORT");
            System.exit(0);
        }

    }

}
