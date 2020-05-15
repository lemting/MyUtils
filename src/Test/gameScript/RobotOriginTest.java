package Test.gameScript;

import java.awt.MouseInfo;
import java.awt.Point;

import gameScript.RobotOrigin;

/**
 * 测试 RobotOrigin
 * 
 * @author Lemting
 */
public class RobotOriginTest
{
	/**
	 * 输出坐标信息
	 * @param p
	 */
	public static void printPoint(Point p)
	{
		System.out.println("[" + p.x + "," + p.y + "]");
	}
	
	public static void main(String[] args)
	{
		RobotOrigin.sleep(2000);
		RobotOrigin.origin = RobotOrigin.getNowPosition();
		printPoint(RobotOrigin.origin);
		for(int i = 0;i < 10;i++)
		{
			RobotOrigin.mouseMove(i * 120, i * 100);
			printPoint(MouseInfo.getPointerInfo().getLocation());
			RobotOrigin.sleep(500);
		}
	}
}
