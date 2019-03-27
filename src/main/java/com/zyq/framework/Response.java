package com.zyq.framework;

import java.io.PrintWriter;

/**
 * Created by whq on 2019/3/24.
 */
public class Response {
    private PrintWriter printWriter;

    public Response(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void write(String massage) {
        printWriter.write(massage);
        printWriter.flush();

    }
}
