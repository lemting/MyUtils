package Test.daZhuZai;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import gameScript.RobotUp;

public class DaZhuZai_ShenPoDan
{	
	private static Point origin;	//坐标原点
	
	private static List<Point> items = new ArrayList<>();	//物品坐标集合
	private static List<Point> huan = new ArrayList<>();	//物品对应的兑换坐标集合
	
	private static final Point flush = new Point(940, 690);	//刷新按钮
	private static final Point check = new Point(739, 503);	//确定按钮
	private static final Point over = new Point(40,1060);
	
	static{
		items.add(new Point(286,155));items.add(new Point(508,155));items.add(new Point(729,155));items.add(new Point(952,155));
		items.add(new Point(286,450));items.add(new Point(508,450));items.add(new Point(729,450));items.add(new Point(952,450));
		huan.add(new Point(242,316));huan.add(new Point(464,316));huan.add(new Point(690,316));huan.add(new Point(918,316));
		huan.add(new Point(242,609));huan.add(new Point(464,609));huan.add(new Point(690,609));huan.add(new Point(918,609));
	}
	
	//判断是否结束
	public static boolean isOver()
	{
		Color cl = RobotUp.getPixelColor(over.x,over.y);
		return (cl.getRed() == 52) && (cl.getGreen() == 52) && (cl.getBlue() == 52);
	}
	
	//开始刷神魄丹	//判断是不是神魄丹: RobotUp.equalsColor(new Color(30,62,125),10, 12, 15) //判断确定是否可点击: [692,512] (140,42,5)
	public static void startShuaPoShenDan()
	{
		while(!isOver())	//直到按下了win键结束,退出刷丹
		{
			for(int i = 0;i < items.size() && !isOver();i++)
			{
				RobotUp.sleep(40,60);
				if(RobotUp.equalsColor(RobotUp.addPosition(origin, items.get(i)),new Color(30,62,125),10, 12, 15)) //若是神魄丹,则购买
				{
					//输出日志
					Color co = RobotUp.getPixelColor(RobotUp.addPosition(origin, items.get(i)));
					System.out.println((i+1)+" ["+items.get(i).x+","+items.get(i).y+"] ("+co.getRed()+","+co.getGreen()+","+co.getBlue()+")");
					//兑换
					RobotUp.mouseMove(RobotUp.addPosition(origin, huan.get(i)));	//鼠标移动到兑换
					RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK,100,200);	//点击
					RobotUp.sleep(40,100);	//暂停  40~100 ms
					if(RobotUp.equalsColor(RobotUp.addPosition(origin,new Point(692,512)),new Color(139,43,5),0,0,0))	//如果确定可以按下
					{
						RobotUp.mouseMove(RobotUp.addPosition(origin,check));	//鼠标移动到确定
						RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK,100,200);	//点击
					}
				}
			}
			System.err.println("---------------------");
			RobotUp.sleep(10,20);
			RobotUp.mouseMove(RobotUp.addPosition(origin, flush));	//鼠标移动到刷新
			RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK,100,200);	//点击
			RobotUp.sleep(400,500);	//暂停 50ms,直到刷新成功
		}
	}
	
	public static void main(String[] args)
	{	
		RobotUp.sleep(3000);
		origin = RobotUp.getNowPosition();	//记录坐标原点
		System.out.println("坐标原点: [" + origin.x + ", " + origin.y + "]");
		startShuaPoShenDan();
	}
}	
