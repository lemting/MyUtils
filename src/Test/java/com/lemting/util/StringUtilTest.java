package com.lemting.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class StringUtilTest {

    public static void stringUtilsTest() {
        String half = " ";
        for (char i = 0x0021;i <= 0x007e;i++) {
            half += i;
        }
        String full = StringUtil.halfToFull(half);
        System.out.println("半角字符: " + half);
        System.out.println("全角字符: " + full);
        System.out.println("还原字符: " + StringUtil.fullToHalf(half));
        String resource = "这都被你发现了啊";
        String target = StringUtil.charsetUTF8ConvertToGBK(resource);
        String resourceNew = StringUtil.charsetGBKConvertToUTF8(target);
        System.out.println(resource + "\n" + target + "\n" + resourceNew);
        System.out.println("-------------------------------------------");
        System.out.println(StringUtil.charsetGBKConvertToUTF8("鏁版嵁瀵圭収澶辫触锛\n" +
                "銆戯紝瀵圭収澶辫触琛岋細銆\n" +
                "瀵圭収缂栫爜銆\n" +
                "銆戯紝瀵圭収澶辫触琛岋細銆"));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        stringUtilsTest();
        String ss = "这都被你发现了啊";
        String s = new String(Base64.getDecoder().decode(Base64.getEncoder().encode(ss.getBytes("UTF-8"))), "UTF-8");
        System.out.println(s);
    }
}
