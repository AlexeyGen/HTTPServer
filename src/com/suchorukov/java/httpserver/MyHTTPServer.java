package com.suchorukov.java.httpserver;


// import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IT-Academy on 03.10.2015.
 */
public class MyHTTPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(81);
            while (true)
            {
                Socket s = serverSocket.accept();

                Thread thread = new Thread(new ClientClass(s));
                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
