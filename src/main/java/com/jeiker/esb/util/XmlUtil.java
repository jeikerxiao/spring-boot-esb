package com.jeiker.esb.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Description: Xml 工具类
 * User: jeikerxiao
 * Date: 2019/7/9 3:34 PM
 */
public class XmlUtil {


    /**
     * XML转对象
     *
     * @param clazz 对象类
     * @param xml   字符串
     * @param <T>   T
     * @return
     */
    public static <T> T parseXml(Class<T> clazz, String xml) {
        //创建解析XML对象
        XStream xStream = new XStream(new DomDriver());
        //处理注解
        xStream.processAnnotations(clazz);
        @SuppressWarnings("unchecked")
        //将XML字符串转为bean对象
                T t = (T) xStream.fromXML(xml);
        return t;
    }

    /**
     * 对象转xml
     *
     * @param obj 对象
     * @return
     */
    public static String toXml(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }

    /**
     * 将符合xml的字符串进行美化，美化后的字符串输出后与xml文件中的效果一样
     *
     * @param str
     * @return
     */
    public static String format(String str) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(str);
        } catch (DocumentException documentexception) {
            documentexception.printStackTrace();
        }
        OutputFormat outputformat = OutputFormat.createPrettyPrint();
        // 这里用于控制xml输出的头信息(如：<?xml version="1.0" encoding="UTF-8"?>)
        // true 表示不输出； false 表示输出
        outputformat.setSuppressDeclaration(true);
        StringWriter stringwriter = new StringWriter();
        XMLWriter xmlwriter = new XMLWriter(stringwriter, outputformat);
        try {
            xmlwriter.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringwriter.toString().trim();
    }

}