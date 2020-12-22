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

    /**
     * 全角字符转半角字符
     * @param fullWidthStr 全角字符
     * @return 半角字符
     */
    public static String fullToHalf(String fullWidthStr) {
        StringBuilder sb = new StringBuilder();
        if (fullWidthStr != null && !fullWidthStr.isEmpty()) {
            for (char c : fullWidthStr.toCharArray()) {
                if (c == 0x3000) {
                    sb.append((char) 0x0020);
                } else if (c >= 0xff01 && c <= 0xff5e) {
                    sb.append((char) (c - 0xfee0));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 半角字符转全角字符
     * @param halfWidthStr 半角字符
     * @return 全角字符
     */
    public static String halfToFull(String halfWidthStr) {
        StringBuilder sb = new StringBuilder();
        if (halfWidthStr != null && !halfWidthStr.isEmpty()) {
            for (char c : halfWidthStr.toCharArray()) {
                if (c == 0x0020) {
                    sb.append((char) 0x3000);
                } else if (c >= 0x0021 && c <= 0x007e) {
                    sb.append((char) (c + 0xfee0));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

}
