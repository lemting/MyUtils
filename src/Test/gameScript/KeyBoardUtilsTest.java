package Test.gameScript;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;

import gameScript.KeyBoardUtils;

public class KeyBoardUtilsTest {
	
	// ?,??,???,????,?????,...
	public static void questionMark() {
		System.out.println("开始: ?,??,???,????,?????,...");
		KeyBoardUtils.delay(3000);
		StringBuilder sb = new StringBuilder();
		String s = "?";
		while(true) {
			KeyBoardUtils.pasteText(sb.append(s).toString());
			KeyBoardUtils.keyPressAndRelease(KeyEvent.VK_ENTER);
			KeyBoardUtils.delay(100);
			Point p = KeyBoardUtils.mousePosition();
			if(p.x < 10 && p.y < 10)
				break;
		}
		System.out.println("结束: '?'个数 " + sb.length());
	}
	
	// 1,2,3,4,...
	public static void numbers() {
		System.out.println("开始: 1,2,3,4,...");
		KeyBoardUtils.delay(3000);
		int i = 1;
		while(true) {
			KeyBoardUtils.pasteText("" + i++);
			KeyBoardUtils.keyPressAndRelease(KeyEvent.VK_ENTER);
			KeyBoardUtils.delay(100);
			Point p = KeyBoardUtils.mousePosition();
			if(p.x < 10 && p.y < 10)
				break;
		}
		System.out.println("结束: 最后输出 " + i);
	}
	
	// 1,1,2,3,5,8,...
	public static void fibonacci() {
		System.out.println("开始: 1,1,2,3,5,8,...");
		KeyBoardUtils.delay(3000);
		BigInteger a = new BigInteger("0"), b = new BigInteger("1");
		while(true) {
			BigInteger temp = new BigInteger(b.toString()); b = b.add(a); a = temp;
			KeyBoardUtils.pasteText("" + a);
			KeyBoardUtils.keyPressAndRelease(KeyEvent.VK_ENTER);
			Point p = KeyBoardUtils.mousePosition();
			if(p.x < 10 && p.y < 10)
				break;
		}
		System.out.println("结束: 最后输出 " + a);
	}
	
	// 开根号
	public static void sqrt(double n) {
		System.out.println("开始: 1,.,7,3,2,0,5,0,8,0,....");
		KeyBoardUtils.delay(3000);
		
		StringBuilder sb = new StringBuilder();

		BigDecimal twenty = new BigDecimal("20"); //20
		BigDecimal hundred = new BigDecimal("100"); //100
		// (dividend / divisor = merchant..remainder)	 eg: (7 / 2 = 2..3)
		BigDecimal merchant = BigDecimal.ZERO; //商
		BigDecimal divisor = BigDecimal.ZERO; //除数
		BigDecimal remainder = BigDecimal.ZERO; //余数
		BigDecimal dividend = new BigDecimal("" + n); //被除数
		KeyBoardUtils.setDefaultDelay(2);
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
			KeyBoardUtils.printText("" + merchant.intValue());
			if(dividend.doubleValue() == n) {
				sb.append(".");
				KeyBoardUtils.printText(".");
			}
			//判断是否需要结束计算
			if(flag) break;
			Point p = KeyBoardUtils.mousePosition();
			if(p.x < 10 && p.y < 10)
				break;
			//获取计算下一位数需要的数据
			remainder = dividend.subtract(divisor.multiply(twenty).add(merchant).multiply(merchant)); //新的余数
			divisor = divisor.multiply(BigDecimal.TEN).add(merchant); //新的除数
			dividend = remainder.multiply(hundred); //新的被除数
		}
		System.out.println("结束: 最后输出 " + sb.toString());
	}
	
	public static void main(String[] args) {
		//KeyBoardUtils.printText("`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXVBNM");
		//sqrt(3) = 1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811
		sqrt(3);
	}
}
