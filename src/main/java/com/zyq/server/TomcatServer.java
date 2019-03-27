package com.zyq.server;

import com.zyq.handler.TomcatServerHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by whq on 2019/3/24.
 */
public class TomcatServer {

    private static ServerSocket serverSocket;

    private static int port = 8080;

    private final static int POOL_SIZE = 15;

    private static ExecutorService executorService;

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        executorService = Executors.newFixedThreadPool(POOL_SIZE);
        System.out.println("tomcat starting port [" + port + "]");

        while (true) {
            socket = serverSocket.accept();
            executorService.execute(new TomcatServerHandler(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        new TomcatServer().start();
    }
}
