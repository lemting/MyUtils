package com.lemting.util;

import java.nio.charset.Charset;

public class StringUtilTest {

    public static void stringUtilsTest() {
        String resource = "这都被你发现了啊";
        String target = StringUtil.charsetConversion(resource, Charset.forName("UTF-8"), Charset.forName("EUC-KR"));
        String resourceNew = StringUtil.charsetConversion(resource, Charset.forName("EUC-KR"), Charset.forName("UTF-8"));
        System.out.println(resource + "\n" + target + "\n" + resourceNew);
    }

    public static void main(String[] args) {
        stringUtilsTest();
    }
}
