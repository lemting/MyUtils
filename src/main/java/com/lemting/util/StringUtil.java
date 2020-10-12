package com.lemting.util;

import java.nio.charset.Charset;

/**
 * 字符串工具类
 */
public class StringUtil {

    // UTF-8 字符编码
    public static Charset UTF8 = Charset.forName("UTF-8");
    // GBK 字符编码
    public static Charset GBK = Charset.forName("GBK");

    /**
     * 字符串 UTF-8 编码转换为 GBK
     * @param resource 源字符串(UTF-8)
     * @return 目标字符串(GBK)
     */
    public static String charsetUTF8ConvertToGBK(String resource) {
        return charsetConversion(resource, UTF8, GBK);
    }

    /**
     * 字符串 GBK 编码转换为 UTF-8
     * @param resource 源字符串(GBK)
     * @return 目标字符串(UTF-8)
     */
    public static String charsetGBKConvertToUTF8(String resource) {
        return charsetConversion(resource, GBK, UTF8);
    }

    /**
     * 字符串编码转换为 UTF-8
     * @param resource 源字符串
     * @param resourceCharSet 源字符串编码
     * @return 目标字符串(UTF-8)
     */
    public static String charsetConvertToUTF8(String resource, Charset resourceCharSet) {
        return charsetConversion(resource, resourceCharSet, UTF8);
    }

    /**
     * 字符串编码转换为 GBK
     * @param resource 源字符串
     * @param resourceCharSet 源字符串编码
     * @return 目标字符串(GBK)
     */
    public static String charsetConvertToGBK(String resource, Charset resourceCharSet) {
        return charsetConversion(resource, resourceCharSet, GBK);
    }


    /**
     * 字符串编码转换
     * @param resource 源字符串
     * @param resourceCharSet 源字符串编码
     * @param targetCharSet 目标字符串编码
     * @return 目标字符串
     */
    public static String charsetConversion(String resource, Charset resourceCharSet, Charset targetCharSet) {
        return new String(resource.getBytes(resourceCharSet), targetCharSet);
    }


}
