package Test.daZhuZai;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;

import gameScript.RobotUp;

public class DaZhuZai_WeiShi
{	
	//模拟单击喂食 (wei: 需要单击的位置)
	public void click(Point wei)
	{
		System.out.println("起始位置: [" + wei.x + "," +  wei.y + "]"); //输出开始单击位置
		int num = 0;
		RobotUp.mouseMove(wei.x, wei.y);
		while(!RobotUp.isMouseMove(wei,5,5) && !RobotUp.equalsColor(new Color(19, 11, 6),2))	//若鼠标移动了/鼠标位置颜色不对,则退出单击循环
		{	
			RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK,50,100);	//模拟鼠标单击释放左键
			RobotUp.sleep(100,300);
			num++;	//记录单击次数
		}
		wei = RobotUp.getNowPosition();
		System.out.println("单击次数: " + num);
		System.out.println("结束位置: [" + wei.x + "," +  wei.y + "]"); //输出结束鼠标位置
	}
	
	
	public static void main(String[] args) throws AWTException
	{
		DaZhuZai_WeiShi ws = new DaZhuZai_WeiShi();
		//3s后程序开始运行
		RobotUp.sleep(3000);
		//在鼠标位置处,单击喂食
		ws.click(RobotUp.getNowPosition());
	}
}
