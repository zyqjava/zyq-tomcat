package com.zyq.framework;

import com.zyq.servlet.HttpServlet;
import com.zyq.servlet.ServletContainer;

import java.util.Map;

/**
 * Created by whq on 2019/3/24.
 */
public class Request {
    private String path;
    private String method;
    private String paramter;

    public HttpServlet initServlet() {
        //通过工具类获取
        HttpServlet httpServlet = ServletContainer.getHttpServlet(path);
        return httpServlet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParamter() {
        return paramter;
    }

    public void setParamter(String paramter) {
        this.paramter = paramter;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    private Map<String, String> attribute;
}
