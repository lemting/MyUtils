package Test.daZhuZai;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Scanner;

import Test.map.Mainland;
import Test.map.MapPositionData;
import Test.map.Region;
import gameScript.RobotOrigin;
import gameScript.RobotUp;

public class DaZhuZai_ShuaJi
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		RobotUp.sleep(2000);
		//根据×的坐标获取坐标原点(鼠标指在×处)
		RobotOrigin.origin = RobotUp.subPosition(MapPositionData.getChaPoint(),RobotUp.getNowPosition());
		sc.nextLine();
		List<Mainland> mainlands = MapPositionData.getMapPositionData();
		Mainland tianLuo = mainlands.get(0);
		//点击 [天罗大陆]
		RobotUp.mouseMove(RobotOrigin.actualPosition(tianLuo.getPosition()));
		RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 100, 300);
		RobotUp.sleep(300,400);
		//点击第一个地区 [大罗峰]
		RobotUp.mouseMove(RobotOrigin.actualPosition(tianLuo.getRegions().get(0).getPosition()));
		RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 100, 300);
		RobotUp.sleep(300,400);
		int i = 0;
		//点击 [天罗大陆] 的每一个小关卡
		for(Region region: tianLuo.getRegions())
		{
			i++;
			//点击每一个小关卡
			for(Point p: region.getRegions())
			{
				//点击关卡
				RobotUp.mouseMove(RobotOrigin.actualPosition(p));
				RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 100, 300);
				RobotUp.sleep(300,400);
				//点击 [×] 关闭关卡
				RobotUp.mouseMove(RobotOrigin.actualPosition(MapPositionData.getChaPoint()));
				RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 100, 300);
				RobotUp.sleep(300,400);
			}
			if(i > 1)
				break;
			//点击 [下一地区]
			RobotUp.mouseMove(RobotOrigin.actualPosition(MapPositionData.getNextPoint()));
			RobotUp.mousePressAndRelease(InputEvent.BUTTON1_DOWN_MASK, 100, 300);
			RobotUp.sleep(300,400);
		}
		sc.close();
	}
}
