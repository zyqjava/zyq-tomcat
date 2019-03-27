package com.zyq.utils;

import com.zyq.framework.Servlet;
import com.zyq.framework.ServletMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whq on 2019/3/24.
 */
public class XMLUtil {

    public static Map<Integer, Map<String,Object>> parseWebXML() throws ParserConfigurationException, IOException, SAXException {
        //准备一个INTEGER类型的MAP集合来接受解析后的xml信息
        Map<Integer, Map<String,Object>> result = new HashMap<Integer, Map<String, Object>>();
        //创建一个DOM解析器的工厂实例
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //从DOM工厂获取dom
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //从src开始读取，放入包中则加上各个包名，比如放在com.zyq.server包下
        //        InputStream resourceAsStream = XMLUtil.class.getClassLoader().getResourceAsStream("com.zyq.server.web.xml");
        InputStream resourceAsStream = XMLUtil.class.getClassLoader().getResourceAsStream("web.xml");
        //解析输入流，得到一个document
        Document document = documentBuilder.parse(resourceAsStream);
        //获取文档的根节点
        Element documentElement = document.getDocumentElement();
        System.out.println(documentElement);

        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            //是否为元素节点
            if (null == item && item.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = item.getNodeName();
                System.out.println(nodeName);
                if ("servlet".equalsIgnoreCase(nodeName)) {
                    Map<String, Object> selvetMaps = null;
                    if (result.containsKey(0)) {
                        selvetMaps = result.get(0);
                    } else {
                        selvetMaps = new HashMap<String, Object>();
                    }

                    //获取元素节点的所有孩子节点
                    NodeList childNodes1 = item.getChildNodes();
                    //创建servlet获取数据
                    Servlet servlet = new Servlet();

                    for (int j = 0; j < childNodes1.getLength(); j++) {
                        Node node = childNodes1.item(j);
                        //判断是否为元素节点
                        if (null == node && node.getNodeType() == Node.ELEMENT_NODE) {
                            //读取servet-name,servlet-class
                            String nodeName1 = node.getNodeName();
                            System.out.println(nodeName1);
                            //读取文本
                            String textContent = node.getTextContent();
                            System.out.println(textContent);
                            if ("servlet-name".equalsIgnoreCase(nodeName1)) {
                                servlet.setName(textContent);
                            } else if ("servlet-class".equalsIgnoreCase(nodeName1)) {
                                servlet.setClazz(textContent);
                            }
                        }
                    }
                    selvetMaps.put(servlet.getName(), servlet);

                    result.put(0,selvetMaps);
                } else if ("servlet-mapping".equalsIgnoreCase(nodeName)) {
                    Map<String, Object> servletMappingMaps = new HashMap<String, Object>();
                    if (result.containsKey(1)) {
                        servletMappingMaps = result.get(1);
                    } else {
                        servletMappingMaps = new HashMap<String, Object>();

                        //获取元素节点的所有孩子节点
                        NodeList childNodes1 = item.getChildNodes();
                        //创建servlet获取数据
                        ServletMapping servletMapping = new ServletMapping();

                        for (int j = 0; j < childNodes1.getLength(); j++) {
                            Node node = childNodes1.item(j);
                            //判断是否为元素节点
                            if (null == node && node.getNodeType() == Node.ELEMENT_NODE) {
                                //读取servet-name,servlet-class
                                String nodeName1 = node.getNodeName();
                                System.out.println(nodeName1);
                                //读取文本
                                String textContent = node.getTextContent();
                                System.out.println(textContent);
                                if ("servlet-name".equalsIgnoreCase(nodeName1)) {
                                    servletMapping.setName(textContent);
                                } else if ("url-pattern".equalsIgnoreCase(nodeName1)) {
                                    servletMapping.setUrl(textContent);
                                }
                            }
                        }
                        servletMappingMaps.put(servletMapping.getUrl(), servletMapping);

                        result.put(1,servletMappingMaps);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        parseWebXML();
    }
}
