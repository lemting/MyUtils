package com.lemting.util;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.dictionary.py.PinyinDictionary;

/**
 * 汉字转拼音, 简繁转换
 * @author Lemting
 */
public class PinyinUtil {
	
	/**
	 * 繁体转简体
	 * @param str
	 * @return
	 */
	public static String toSC(String str) {
		return HanLP.convertToSimplifiedChinese(str);
	}
	
	/**
	 * 简体转繁体
	 * @param str
	 * @return
	 */
	public static String toTC(String str) {
		return HanLP.convertToTraditionalChinese(str);
	}
	
	/**
	 * 转拼音, 默认 拼音(无音调), 空格分隔, 显示标点符号
	 * @param str
	 * @return
	 */
	public static String toPinyin(String str) {
		return toPinyin(str, 3, " ", false);
	}
	
	/**
	 * 转拼音
	 * @param str 
	 * @param type 1: 拼音(数字音调), 2: 拼音(符号音调), 3: 拼音(无音调), 4: 拼音(首字母), 5: 声调, 6: 声母, 7: 韵母
	 * @param separator 分割符
	 * @param remainNone 有些字没有拼音(如标点), 是否保留它们(用none表示)
	 * @return
	 */
	public static String toPinyin(String str, int type, String separator, boolean remainNone) {
		List<Pinyin> list = PinyinDictionary.convertToPinyin(str);
		int length = list.size();
        StringBuilder result = new StringBuilder(length * (5 + separator.length()));
        int i = 1;
        for (Pinyin pinyin : list) {
			if (pinyin == Pinyin.none5 && !remainNone) 
				result.append(str.charAt(i - 1)); 
			else {
				switch (type) {
					case 1: result.append(pinyin.toString()); break; // 拼音(数字音调)
					case 2: result.append(pinyin.getPinyinWithToneMark()); break; // 拼音(符号音调)
					case 3: result.append(pinyin.getPinyinWithoutTone()); break; // 拼音(无音调)
					case 4: result.append(pinyin.getFirstChar()); break; // 拼音(首字母)
					case 5: result.append(pinyin.getTone()); break; // 声调
					case 6: result.append(pinyin.getShengmu()); break; // 声母
					case 7: result.append(pinyin.getYunmu()); break; // 韵母
				}
			}
			i++;
			result.append(separator);
		}
		if (result.length() > separator.length()) 
			result.delete(result.length() - separator.length(), result.length());
		return result.toString();
	}
}
