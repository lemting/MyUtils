package gameScript;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 * RobotUp: 功能更强大的 Robot(在原来  Robot 类的基础上新增一些方法,并将所有方法静态)
 * 
 * @author Lemting
 * @Time 2018-11-17
 * 
 * 静态方法: 
 * 	  ----------------------------------------------------------------------
 * 		Color getPixelColor(int x, int y): 返回给定屏幕坐标处的像素颜色
 * 		
 * 		void keyPress(int keycode): 按一个给定的键
 *		void keyRelease(int keycode): 释放给定的键
 *
 * 		void mouseMove(int x, int y): 将鼠标指针移动到给定的屏幕坐标
 *		void mousePress(int buttons): 按一个或多个鼠标按钮
 *		void mouseRelease(int buttons): 释放一个或多个鼠标按钮
 *		void mouseWheel(int wheelAmt): 旋转鼠标滚轮	(wheelAmt: 移动鼠标滚轮的"凹口"数量,负值表示向上/远离用户的移动,正值表示向下/朝向用户移动)
 * 	  --------- ↑↑↑↑ Robot 类方法  ↑↑↑↑ -------------- ↓↓↓↓ 新增方法 ↓↓↓↓ ---------
 * 		void keyPressAndRelease(int keycode,int down,int up): 键盘按下并释放键 ,期间随机延迟[down,up]毫秒
 * 		void mousePressAndRelease(int buttons,int down,int up): 鼠标按下并释放键 ,期间随机延迟[down,up]毫秒
 * 
 * 		void sleep(int ms): 睡眠指定时间
 * 		void sleep(int down,int up): 睡眠指定范围的随机时间: [down,up] ms
 * 
 * 		Point getNowPosition(): 获取鼠标当前位置		
 * 		Color getNowPositionColor(): 获取鼠标当前位置的颜色
 * 
 * 		boolean isMouseMove(Point old,int m,int n): 判断鼠标是否移动(old: 一开始的位置; m,n: x,y坐标允许的偏移)
 * 		boolean equalsColor(Color color,int n): 鼠标位置是否为指定颜色(color: 指定要比较的颜色 ; n: 允许的偏移)
 * 	  ----------------------------------------------------------------------
 */

public class RobotUp
{
	//Robot 类对象,用于模拟键盘/鼠标操作
	public static Robot robot;
	//记录鼠标当前位置(每次调用  getNowPoint()刷新)
	private static Point nowPoint;
	//初始化 robot
	static{try{robot = new Robot();}catch(AWTException e){e.printStackTrace();}}
	
	/**
	 * 睡眠指定时间
	 * @param ms 睡眠的时间(毫秒)
	 */
	public static void sleep(int ms) {robot.delay(ms);}
	
	/**
	 * 睡眠指定范围的随机时间: [down,up] ms
	 * @param down 时间的下限
	 * @param up 时间的上限
	 */
	public static void sleep(int down,int up){robot.delay((int)(Math.random() * (up + 1 - down)) + down);}
	
	/**
	 * 获取鼠标当前位置
	 * @return 当前鼠标位置
	 */
	public static Point getNowPosition()
	{
		nowPoint = MouseInfo.getPointerInfo().getLocation();
		return nowPoint;
	}

	/**
	 * 获取鼠标当前位置的颜色
	 * @return 当前鼠标位置的颜色
	 */
	public static Color getNowPositionColor()
	{
		getNowPosition();
		return robot.getPixelColor(nowPoint.x, nowPoint.y);
	}
	
	/**
	 * 判断鼠标是否移动
	 * @param old 鼠标原来的位置
	 * @param m x坐标允许的偏移
	 * @param n y坐标允许的偏移
	 * @return 鼠标是否在误差允许的范围内移动了
	 */
	public static boolean isMouseMove(Point old,int m,int n)
	{
		boolean flag = false;
		getNowPosition();
		if(nowPoint.x > old.x + m || nowPoint.x < old.x - m)
			flag = true;
		if(nowPoint.y > old.y + n || nowPoint.y < old.y - n)
			flag = true;
		return flag;
	}
	
	/**
	 * 鼠标位置是否为指定颜色
	 * @param color 要比较的指定颜色
	 * @param n r,g,b允许的颜色误差偏移
	 * @return 鼠标位置的颜色是否为在误差允许的范围内的颜色
	 */
	public static boolean equalsColor(Color color,int n)
	{
		boolean flag = true;
		getNowPosition();
		Color c = robot.getPixelColor(nowPoint.x,nowPoint.y);
		if(color.getRed() - n > c.getRed() || c.getRed() > color.getRed() + n)
			flag = false;
		if(color.getBlue() - n > c.getBlue() || c.getBlue() > color.getBlue() + n)
			flag = false;
		if(color.getGreen() - n > c.getGreen() || c.getGreen() > color.getGreen() + n)
			flag = false;
		return flag; 
	}
	
	//键盘按下并释放键 ,期间随机延迟[down,up]毫秒
	/**
	 * 键盘按下键,并在指定区间内随机的时间延迟下,释放键
	 * @param keycode 按下的键
	 * @param down 延迟时间的下限
	 * @param up 延迟时间的上限
	 */
	public static void keyPressAndRelease(int keycode,int down,int up) 
	{
		robot.keyPress(keycode);
		sleep(down,up);
		robot.keyRelease(keycode);
	}

	/**
	 * 鼠标按下键,并在指定区间内随机的时间延迟下,释放键
	 * @param buttons 按下的键
	 * @param down 延迟时间的下限
	 * @param up 延迟时间的上限
	 */
	public static void mousePressAndRelease(int buttons,int down,int up)
	{
		robot.mousePress(buttons);
		sleep(down,up);
		robot.mouseRelease(buttons);
	}
	
	//获取指定位置的颜色
	public static Color getPixelColor(int x,int y) {return robot.getPixelColor(x, y);}
	//模拟键盘按下键
	public static void keyPress(int keycode) {robot.keyPress(keycode);}
	//模拟键盘释放键
	public static void keyRelease(int keycode) {robot.keyRelease(keycode);}
	//模拟鼠标移动
	public static void mouseMove(int x,int y) {robot.mouseMove(x, y);}
	//模拟鼠标按下键
	public static void mousePress(int buttons) {robot.mousePress(buttons);}
	//模拟鼠标释放键
	public static void mouseRelease(int buttons) {robot.mouseRelease(buttons);}
	//模拟鼠标滚轮
	public static void mouseWheel(int wheelAmt) {robot.mouseWheel(wheelAmt);} 
}