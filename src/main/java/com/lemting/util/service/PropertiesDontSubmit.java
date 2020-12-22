package com.lemting.util.service;

//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义配置
 */
//@RequestMapping("/p")
//@Controller
public class PropertiesDontSubmit /* implements InitializingBean */ {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final Formatter format = new Formatter();

    private static Map<String, String> properties = null;

    private static final Charset UTF8 = Charset.forName("utf-8");
    private static final Charset GBK = Charset.forName("gbk");

    public static String getTime() {
        return SDF.format(new Date());
    }

    private static void initProperties() {
        String path = "D:\\Hundsun\\Code\\test\\src\\main\\resources\\properties.properties";
        properties = readFile(path);
    }

    public static String getProperty(String property) {
        return getProperty(property, "");
    }

    public static String getProperty(String property, String defaultVal) {
        if (property == null) return defaultVal;
        String res = properties.get(property);
        return res == null || res.isEmpty() ? defaultVal : res;
    }

    public static String getSyncProperty(String property) {
        return getSyncProperty(property, "");
    }

    public static String getSyncProperty(String property, String defaultVal) {
        initProperties();
        if (property == null) return defaultVal;
        String res = properties.get(property);
        return res == null || res.isEmpty() ? defaultVal : res;
    }

    public static boolean equalsProperty(String property, String value) {
        return equalsProperty(property, value, false);
    }

    public static boolean equalsProperty(String property, String value, boolean ignoreCase) {
        return value != null && ignoreCase ? value.equalsIgnoreCase(getProperty(property)) : value.equals(getProperty(property));
    }

    public static boolean equalsSyncProperty(String property, String value) {
        return equalsSyncProperty(property, value, false);
    }

    public static boolean equalsSyncProperty(String property, String value, boolean ignoreCase) {
        return value != null && ignoreCase ? value.equalsIgnoreCase(getSyncProperty(property)) : value.equals(getSyncProperty(property));
    }

    public static Map<String, String> readFile(String path) {
        return readFile(path, UTF8, false);
    }

    public static Map<String, String> readFile(String path, boolean append) {
        return readFile(path, UTF8, append);
    }

    public static Map<String, String> readFile(String path, String charSet, boolean append) {
        return readFile(path, Charset.forName(charSet), append);
    }

    public static Map<String, String> readFile(String path, Charset charSet, boolean append) {
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

    public static String toString(Map<String, String> map) {
        return toString(map, '\n');
    }

    public static String toString(Map<String, String> map, char split) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(split);
        }
        return sb.toString();
    }

    public static String toString(Map<String, String> map, String split) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(split);
        }
        return sb.toString();
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        initProperties();
//    }

//    @RequestMapping(value = {"/f.json", "/flush.json"})
//    @ResponseBody
//    public String flush() {
//        initProperties();
//        return properties == null ? "null" : getTime() + ", 数量: " + properties.size() + "\n\n" + toString(properties);
//    }

//    @RequestMapping(value = {"/a.json", "/all.json"})
//    @ResponseBody
//    public String all() {
//        return properties == null ? "null" : "数量: " + properties.size() + "\n\n" + toString(properties);
//    }

//    @RequestMapping(value = {"/{name}/g.json", "/{name}/get.json"})
//    @ResponseBody
//    public String get(@PathVariable("name") String name) {
//        return properties == null ? "null" : name + ": " + getProperty(name);
//    }
}
