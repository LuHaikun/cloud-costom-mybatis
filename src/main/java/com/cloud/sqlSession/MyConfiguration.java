package com.cloud.sqlSession;

import com.cloud.config.Function;
import com.cloud.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 10:38 AM
 * @Description: 配置类
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class MyConfiguration {
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();
    /**
     * 读取xml信息并处理
     */
    public Connection build (String resource) {
        InputStream stream = loader.getResourceAsStream(resource);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            return parserDataSource(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection parserDataSource (Element node) throws ClassNotFoundException{
        if (!node.getName().equals("database")) {
            throw new RuntimeException("root should be <databese>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        for (Object item : node.elements("property")) {
            Element element = (Element) item;
            String value = getElementValue(element);
            String keyType = element.attributeValue("name");
            if (keyType == null || value == null) {
                throw new RuntimeException("database: <property> should contain name and value");
            }
            switch (keyType) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                default:
                    throw new RuntimeException("database: <property> unknown name ");
            }
        }
        Class.forName(driverClassName);
        Connection connection = null;
        try {
            //建立数据库链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getElementValue (Element element) {
        return element.hasContent() ? element.getText() : element.attributeValue("value");
    }

    //用面向对象的思想设计读取xml配置
    public MapperBean readMapper (String mapperPath) {
        MapperBean mapper = new MapperBean();
        InputStream stream = loader.getResourceAsStream(mapperPath);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            mapper.setInterfaceName(root.attributeValue("namespace").trim());
            List<Function> list = new ArrayList<Function>();
            Iterator iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Function fun = new Function();
                Element element = (Element) iterator.next();
                String sqlType = element.getName().trim();
                String funcName = element.attributeValue("id").trim();
                String sql = element.getText().trim();
                String resultType = element.attributeValue("resultType").trim();
                fun.setSqlType(sqlType);
                fun.setFuncName(funcName);
                Object newInstance = null;
                try {
                    newInstance = Class.forName(resultType).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                fun.setResultType(newInstance);
                fun.setSql(sql);
                list.add(fun);
            }
            mapper.setList(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapper;
    }
}
