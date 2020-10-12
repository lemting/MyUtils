package com.lemting.util.endecode;

import java.util.regex.Pattern;

/**
 * 摩斯电码
 * @author Lemting
 *
 */
public class Morse {

	// 电码
	public static final String[] code = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--."
			, "....", "..", ".---", "-.-", ".-..", "--", "-."
			, "---", ".--.", "--.-", ".-.", "...", "-"
			, "..-", "...-", ".--", "-..-", "-.--", "--.."
			, ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "..--..", "-..-.", "-...-", ".-.-.-"};
	// 字符
	public static final char[] chs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G'
			, 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
			'O', 'P', 'Q', 'R', 'S', 'T'
			, 'U', 'V', 'W', 'X', 'Y', 'Z'
			, '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '?', '/', '-', '.' };
	
	/**
	 * 编码, 默认使用 .-
	 * @param src 要编码的字符串
	 * @return 电码
	 */
	public static String encode(String src) {
		return encode(src, '.', '-', " ");
	}
	
	/**
	 * 编码
	 * @param src 要编码的字符串
	 * @param dot 替代'.'的字符
	 * @param minus 替代'-'的字符
	 * @param regex 分隔符
	 * @return 电码
	 */
	public static String encode(String src, char dot, char minus, String regex) {
		if (src == null || src.isBlank()) {
			return "";
		}
		if (!src.matches("[.A-Za-z0-9\\\\?/\\\\-]+")) {
			throw new RuntimeException("格式错误");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i < src.length();i++) {
			int index = getIndex(src.charAt(i));
			if (index == -1) {
				throw new RuntimeException("格式错误");
			}
			sb.append(code[index]).append(regex);
		}
		sb.delete(sb.length() - regex.length(), sb.length());
		String result = sb.toString();
		if (dot != '.') result = result.replaceAll("\\.", "" + dot);
		if (minus != '-') result = result.replaceAll("\\-", "" + minus);
		return result;
	}
	
	/**
	 * 解码, 默认使用 .-
	 * @param src 要解码的电码
	 * @return 原字符串
	 */
	public static String decode(String src) {
		return decode(src, '.', '-', " ");
	}
	
	/**
	 * 解码
	 * @param src 要解码的电码
	 * @param dot 替代'.'的字符
	 * @param minus 替代'-'的字符
	 * @param regex 分隔符
	 * @return 原字符串
	 */
	public static String decode(String src, char dot, char minus, String regex) {
		if (src == null || src.isBlank()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		String[] strs = src.split(Pattern.quote(regex));
		if (strs == null || strs.length == 0) {
			throw new RuntimeException("格式错误");
		}
		for (String s: strs) {
			if (dot != '.') s = s.replaceAll(Pattern.quote("" + dot), ".");
			if (minus != '-') s = s.replaceAll(Pattern.quote("" + minus), "-");
			if (!s.matches("[.-]+")) {
				throw new RuntimeException("格式错误");
			}
			int index = getIndex(s);
			if (index == -1) {
				throw new RuntimeException("格式错误");
			}
			sb.append(chs[index]);
		}
		return sb.toString();
	}
	
	// 获取指定字符在数组中的索引
	public static int getIndex(char c) {
		for (int i = 0;i < chs.length;i++) {
			if (c >= 'a' && c <= 'z') {
				c -= 32;
			}
			if (chs[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	// 获取指定电码在数组中的索引
	public static int getIndex(String s) {
		if (s != null && !s.isBlank()) {
			for (int i = 0;i < chs.length;i++) {
				if (code[i].equals(s))
					return i;
			}
		}
		return -1;
	}
}
