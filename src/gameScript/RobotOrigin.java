package gameScript;

import java.awt.MouseInfo;
import java.awt.Point;

public class RobotOrigin extends RobotUp
{
	//坐标原点
	public static Point origin = new Point(0,0);
	
	public static void setOrigin(Point origin)
	{
		RobotOrigin.origin = origin;
	}

	public static Point getNowPosition()
	{
		nowPoint = addPosition(origin,MouseInfo.getPointerInfo().getLocation());
		return nowPoint;
	}
	
	public static Point actualPosition(Point relative)
	{
		return new Point(origin.x + relative.x,origin.y + relative.y);
	}
}
