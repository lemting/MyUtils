package com.lemting.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数值工具类
 */
public class MathUtil {

	/**
	 * byte 转二进制
	 * @param b byte
	 * @return
	 */
	public static String byteToBinary(byte b) {
		return Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
	}

	/**
	 * 开根号
	 * @param n 要开跟的小数
	 * @param fraction 精确到小数点后几数(不能小于0)
	 * @return
	 */
	public static String sqrt(double n, int fraction) {
		if(n < 0 || fraction < 0)
			return "";
		StringBuilder sb = new StringBuilder();
		BigDecimal twenty = new BigDecimal("20"); //20
		BigDecimal hundred = new BigDecimal("100"); //100
		// (dividend / divisor = merchant..remainder)	 eg: (7 / 2 = 2..3)
		BigDecimal divisor = new BigDecimal((int) Math.sqrt(n)); //除数
		BigDecimal merchant = BigDecimal.ZERO; // 商
		BigDecimal remainder = new BigDecimal("" + n).subtract(divisor.multiply(divisor)); //余数
		BigDecimal dividend = remainder.multiply(hundred); //被除数
		sb.append(divisor).append(".");
		int num = 0;
		//num=0  时, 计算整数部分; num>0  时, 计算小数点后 num 位; num 最大为(fraction+1) 位便于四舍五入
		while(true) {
			boolean flag = false; //记录是否除尽
			//计算下一位数
			for(int i = 1;i <= dividend.doubleValue()/2 + 1;i++) {
				BigDecimal iVal = new BigDecimal("" + i);
				int temp = (divisor.multiply(twenty).add(iVal)).multiply(iVal).compareTo(dividend); //(除数 * 20 + 商) * 商
				if(temp == 0) {
					merchant = iVal; //新的商
					flag = true;
					break;
				} else if(temp > 0) {
					merchant = iVal.subtract(BigDecimal.ONE); //新的商
					break;
				}
			}
			//保存该数
			sb.append(merchant.intValue());
			//判断是否需要结束计算
			if(flag || num++ > fraction + 1) break;
			//获取计算下一位数需要的数据
			remainder = dividend.subtract(divisor.multiply(twenty).add(merchant).multiply(merchant)); //新的余数
			divisor = divisor.multiply(BigDecimal.TEN).add(merchant); //新的除数
			dividend = remainder.multiply(hundred); //新的被除数
		}
		BigDecimal result = new BigDecimal(sb.toString()).divide(BigDecimal.ONE, fraction, RoundingMode.HALF_UP);
		return result.toString();
	}
	
}
