package Test.yongJiChengShi;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import gameScript.RobotOrigin;
import gameScript.RobotUp;

public class SaiChe
{
	//左 按钮
	private static final Point left = new Point();
	//右 按钮
	private static final Point rigth = new Point();
	//操作指令集合
	private static final List<Point> operation = new ArrayList<>();
	//正常颜色集合
	private static final List<Color> blueList = new ArrayList<>();
	//正确颜色集合
	private static final List<Color> greenList = new ArrayList<>();
	//错误颜色集合
	private static final List<Color> redList = new ArrayList<>();
	//结束标志坐标
	private static final Point over = new Point(40,1060);
	//第一辆车的坐标
	private static final Point oneCar = new Point(445,345);
	
	static{
		operation.add(new Point(131,328)); operation.add(new Point(199,328)); operation.add(new Point(266,328));
		operation.add(new Point(334,328)); operation.add(new Point(402,328)); operation.add(new Point(470,328)); operation.add(new Point(438,328));
		blueList.add(new Color(48,194,255));  blueList.add(new Color(50,161,255));
		greenList.add(new Color(133,217,43)); greenList.add(new Color(98,195,36));
		redList.add(new Color(221,52,52));    redList.add(new Color(198,42,42));
	}
	
	//判断是否结束
	@SuppressWarnings("unlikely-arg-type")
	public static boolean gameOver()
	{
		Color c = RobotUp.getPixelColor(over.x,over.y);
		if((c.getRed() == 52) && (c.getGreen() == 52) && (c.getBlue() == 52))
			return true;
		Point p = operation.get(0);
		return !(blueList.contains(p) || greenList.contains(p) || redList.contains(p));
	}
	
	//判断颜色是否与集合中的颜色匹配
	public static boolean yesColor(List<Color> list,Point now,int r,int g,int b)
	{
		for(Color c: list)
		{
			if(RobotOrigin.equalsColor(now, c, r, g, b))
				return true;
		}
		return false;
	}
	
	public static void start()
	{
		RobotOrigin.mouseMove(oneCar);
		System.out.println("第一辆车: " + RobotOrigin.getNowPosition());
		RobotOrigin.sleep(1000);
		RobotOrigin.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 20, 30);
		RobotOrigin.sleep(3500);
		while(!gameOver())
		{
			for(int i = 0;i < operation.size();i++)
			{
				Point p = operation.get(i);
				if(yesColor(blueList, p, 5, 5, 5))
				{
					if(RobotOrigin.equalsColor(p, blueList.get(0), 5, 5, 5))  //左按钮
					{
						System.out.println("左: " + p);
						RobotOrigin.mouseMove(left);
					}
					else if(RobotOrigin.equalsColor(p, blueList.get(1), 5, 5, 5))  //右按钮
					{
						System.out.println("右: " + p);
						RobotOrigin.mouseMove(rigth);
					}
					else 
						continue;
					RobotOrigin.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 20, 30);
					RobotOrigin.sleep(100,200);
					if(yesColor(redList, p, 5, 5, 5))
						break;
				}
				else if(yesColor(redList, p, 5, 5, 5))
					break;
			}
		}		
	}
	
	public static void main(String[] args) throws Exception
	{
		Robot robot = new Robot();
		robot.delay(1000);
		//RobotOrigin.origin = RobotUp.getNowPosition();
		//System.out.println("坐标原点: " + RobotOrigin.origin);
		
		for(int i = 0;i < 1;i++)
		{
			robot.mouseMove(560, 560);
			System.out.println("第一辆车: " + MouseInfo.getPointerInfo().getLocation());
			robot.delay(10);
		}
	}
}
