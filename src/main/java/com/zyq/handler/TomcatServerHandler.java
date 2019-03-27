package com.zyq.handler;

import com.zyq.framework.Request;
import com.zyq.framework.Response;
import com.zyq.servlet.HttpServlet;

import java.io.*;
import java.net.Socket;

/**
 * Created by whq on 2019/3/24.
 */
public class TomcatServerHandler implements Runnable {
    private Socket socket;

    private PrintWriter printWriter;

    public TomcatServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            //模拟请求头
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            printWriter.println("HTTP/1.1 200  ok ");
            printWriter.println("Content - Type: text/html;charset = UTF-8");
            printWriter.println();

            //封装request和response
            Request request = new Request();
            Response response = new Response(printWriter);

            //读取请求数据
            InputStream inputStream =socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while (true) {
                String massage = reader.readLine();
                if (null == massage || "".equals(massage.trim())) {
                    break;
                }

                String[] massages = massage.split(" ");

                if (3 == massages.length && "HTTP/1.1".equalsIgnoreCase(massages[2])) {
                    request.setMethod(massages[0]);
                    request.setPath(massages[1]);
                    break;
                }
                System.out.println(massage);
                /*printWriter.println("<h>Hello! world</h>");*/
            }

            if (request.getPath().endsWith("ico")) {
                return;
            }

            //加载servlet
            HttpServlet httpServlet = request.initServlet();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
