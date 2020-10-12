package com.lemting.util.endecode;

public class MorseTest {

	public static void main(String[] args) {
		String s = "ZHONGYUANANKANG";
		char dot = 'o';
		char minus = '^';
		String regex = "><";
		String code = Morse.encode(s, dot, minus, regex);
		String ss = Morse.decode(code, dot, minus, regex);
		System.out.println(code);
		System.out.println(ss);
	}
}
