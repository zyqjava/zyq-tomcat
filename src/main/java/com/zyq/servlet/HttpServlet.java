package com.zyq.servlet;

import com.zyq.framework.Request;
import com.zyq.framework.Response;

import java.io.IOException;

/**
 * Created by whq on 2019/3/24.
 */
public abstract class HttpServlet {
    public void doGet(Request request, Response response) throws IOException {
        this.service(request, response);
    }
    public void doPost(Request request, Response response) throws IOException {
        this.service(request, response);
    }
    public void service(Request request, Response response) throws IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }
}
