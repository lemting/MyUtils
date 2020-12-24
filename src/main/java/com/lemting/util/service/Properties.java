package com.lemting.util.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;

public class Properties /* implements InitializingBean */ {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static Map<String, Map<String, String>> properties = null;

    private static final Charset UTF8 = Charset.forName("utf-8");
    private static final Charset GBK = Charset.forName("gbk");

    public static String getTime() {
        return SDF.format(new Date());
    }

    public static void initProperties() {
        String path = "src/main/resources/properties.xml";
        properties = readXml(path);
    }

    public static String getPropertyValue(String property) {
        return getPropertyValue(property, "");
    }

    public static String getPropertyValue(String property, String defaultVal) {
        if (property == null) return defaultVal;
        Map<String, String> proMap = properties.get(property);
        String res = null;
        if (proMap != null && proMap.size() > 0) {
            res = proMap.get("value");
        }
        return res == null || res.isEmpty() ? defaultVal : res;
    }

    public static boolean equalsPropertyValue(String property, String value) {
        return equalsPropertyValue(property, value, false);
    }

    public static boolean equalsPropertyValue(String property, String value, boolean ignoreCase) {
        return value != null && ignoreCase ? value.equalsIgnoreCase(getPropertyValue(property)) : value.equals(getPropertyValue(property));
    }

    public static Map<String, String> readProperties(String path) {
        return readProperties(path, UTF8, false);
    }

    public static Map<String, String> readProperties(String path, boolean append) {
        return readProperties(path, UTF8, append);
    }

    public static Map<String, String> readProperties(String path, String charSet, boolean append) {
        return readProperties(path, Charset.forName(charSet), append);
    }

    public static Map<String, String> readProperties(String path, Charset charSet, boolean append) {
        System.err.printf("%s 开始读取配置文件[%s]\n", getTime(), path);
        long start = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charSet));
            String line = null;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int i = line.indexOf("=");
                if (i > 0) {
                    String name = line.substring(0, i).trim();
                    if (name.isEmpty()) continue;
                    String value = line.substring(i + 1).trim();
                    map.put(name, map.containsKey(name) ? (map.get(name) + (!append || value.isEmpty() || ("," + map.get(name) + ",").contains("," + value + ",") ? "" : ("," + value))) : value);
                }
            }
            long end = System.currentTimeMillis();
            System.err.printf("%s 结束读取配置文件[%s], 解析时间[%d]ms\n", getTime(), path, end - start);
            return map;
        } catch (Exception e) {
            System.err.printf("%s 读取配置文件[%s]出错\n", getTime(), path);
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.printf("%s 读取配置文件[%s], 关闭文件流出错\n", getTime(), path);
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Map<String, Map<String, String>> readXml(String path) {
        return readXml(path, UTF8);
    }

    public static Map<String, Map<String, String>> readXml(String path, String charSet) {
        return readXml(path, Charset.forName(charSet));
    }

    public static Map<String, Map<String, String>> readXml(String path, Charset charSet) {
        System.err.printf("%s 开始读取配置文件[%s]\n", getTime(), path);
        long start = System.currentTimeMillis();
        Map<String, Map<String, String>> map = new HashMap<>();
        SAXReader saxreader = new SAXReader();
        try {
            Document document = saxreader.read(new File(path));
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator("property");
            while (iterator.hasNext()) {
                Element property = iterator.next();
                Map<String, String> propertyMap = new HashMap<>();
                String name = property.attributeValue("name");
                if (name != null && !name.isEmpty()) {
                    String info = property.attributeValue("info");
                    String value = property.attributeValue("value");
                    String detail = property.getText();
                    propertyMap.put("name", name);
                    propertyMap.put("info", info == null ? "" : info);
                    propertyMap.put("value", value == null ? "" : value);
                    propertyMap.put("detail", detail == null ? "" : detail);
                    map.put(name, propertyMap);
                }
            }
            long end = System.currentTimeMillis();
            System.err.printf("%s 结束读取配置文件[%s], 解析时间[%d]ms\n", getTime(), path, end - start);
            return map;
        } catch (DocumentException e) {
            System.err.printf("%s 读取配置文件[%s]出错\n", getTime(), path);
            e.printStackTrace();
        }
        return map;
    }

    public static Comparator<Map<String, String>> propertyComparator = (property1, property2) -> {
        String a = property1 != null && property1.get("name") != null ? property1.get("name") : "";
        String b = property2 != null && property2.get("name") != null ? property2.get("name") : "";
        return a.compareTo(b);
    };

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        initProperties();
//    }
}
