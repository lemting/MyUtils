package gameScript;

import java.awt.Color;
import java.awt.Point;

/**
 * RobotOrigin: 拥有坐标系的 RobotUp, 设置坐标原点重写 RobotUp 方法
 * 
 * @author Lemting
 * @Time 2019-01-30
 * 
 */
public class RobotOrigin extends RobotUp
{
	// 坐标原点
	public static Point origin = new Point(0, 0);
	// 记录鼠标当前位置(相对坐标)(每次调用 getNowPosition()刷新)
	public static Point nowPosition = new Point(0, 0);

	/**
	 * 获取鼠标当前位置(相对坐标)
	 * 
	 * @return 当前鼠标位置
	 */
	public static Point getNowPosition()
	{
		nowPosition = RobotUp.getNowPosition();
		nowPosition = subPosition(nowPosition);
		return nowPosition;
	}

	/**
	 * 获取鼠标当前位置的颜色(相对坐标)
	 * 
	 * @return 当前鼠标位置的颜色
	 */
	public static Color getNowPositionColor()
	{
		nowPosition = getNowPosition();
		return getPixelColor(nowPosition.x, nowPosition.y);
	}

	/**
	 * 获取指定位置的颜色(相对坐标)
	 * 
	 * @param position
	 *            坐标
	 * @return 坐标位置的颜色
	 */
	public static Color getPixelColor(Point position)
	{
		return robot.getPixelColor(origin.x + position.x, origin.y + position.y);
	}

	/**
	 * 获取指定位置的颜色(相对坐标)
	 * 
	 * @param x
	 * @param y
	 * @return 坐标位置的颜色
	 */
	public static Color getPixelColor(int x, int y)
	{
		return robot.getPixelColor(origin.x + x, origin.y + y);
	}

	/**
	 * 判断鼠标是否移动 (相对坐标)
	 * 
	 * @param old
	 *            鼠标原来的位置
	 * @param m
	 *            x坐标允许的偏移
	 * @param n
	 *            y坐标允许的偏移
	 * @return 鼠标是否在误差允许的范围内移动了
	 */
	public static boolean isMouseMove(Point old, int m, int n)
	{
		boolean flag = false;
		nowPosition = getNowPosition();
		if (nowPosition.x > old.x + m || nowPosition.x < old.x - m)
			flag = true;
		if (nowPosition.y > old.y + n || nowPosition.y < old.y - n)
			flag = true;
		return flag;
	}

	/**
	 * 鼠标位置是否为指定颜色 (相对坐标)
	 * 
	 * @param color
	 *            要比较的指定颜色
	 * @param n
	 *            r,g,b允许的颜色误差偏移
	 * @return 鼠标位置的颜色是否为在误差允许的范围内的颜色
	 */
	public static boolean equalsColor(Color color, int n)
	{
		boolean flag = true;
		nowPosition = getNowPosition();
		Color c = getPixelColor(nowPosition.x, nowPosition.y);
		if (color.getRed() - n > c.getRed() || c.getRed() > color.getRed() + n)
			flag = false;
		if (color.getBlue() - n > c.getBlue() || c.getBlue() > color.getBlue() + n)
			flag = false;
		if (color.getGreen() - n > c.getGreen() || c.getGreen() > color.getGreen() + n)
			flag = false;
		return flag;
	}

	/**
	 * 鼠标位置是否为指定颜色(相对坐标)
	 * 
	 * @param color
	 *            要比较的指定颜色
	 * @param r
	 *            r允许的偏移
	 * @param g
	 *            g允许的偏移
	 * @param b
	 *            b允许的偏移
	 * @return 颜色是否匹配
	 */
	public static boolean equalsColor(Color color, int r, int g, int b)
	{
		boolean flag = true;
		nowPosition = getNowPosition();
		Color c = getPixelColor(nowPosition.x, nowPosition.y);
		if (color.getRed() - r > c.getRed() || c.getRed() > color.getRed() + r)
			flag = false;
		if (color.getGreen() - g > c.getGreen() || c.getGreen() > color.getGreen() + g)
			flag = false;
		if (color.getBlue() - b > c.getBlue() || c.getBlue() > color.getBlue() + b)
			flag = false;
		return flag;
	}

	/**
	 * 指定位置是否为指定颜色(相对坐标)
	 * 
	 * @param now
	 *            指定的坐标
	 * @param color
	 *            要比较的指定颜色
	 * @param r
	 *            r允许的偏移
	 * @param g
	 *            g允许的偏移
	 * @param b
	 *            b允许的偏移
	 * @return 颜色是否匹配
	 */
	public static boolean equalsColor(Point now, Color color, int r, int g, int b)
	{
		boolean flag = true;
		Color c = getPixelColor(now.x, now.y);
		if (color.getRed() - r > c.getRed() || c.getRed() > color.getRed() + r)
			flag = false;
		if (color.getGreen() - g > c.getGreen() || c.getGreen() > color.getGreen() + g)
			flag = false;
		if (color.getBlue() - b > c.getBlue() || c.getBlue() > color.getBlue() + b)
			flag = false;
		return flag;
	}

	/**
	 * 获取实际坐标的相对坐标
	 * 
	 * @param absolutely
	 *            实际坐标点
	 * @return 相对坐标
	 */
	public static Point subPosition(Point absolutely)
	{
		return new Point(absolutely.x - origin.x, absolutely.y - origin.y);
	}

	/**
	 * 获取相对坐标的实际坐标
	 * 
	 * @param relative
	 *            相对坐标
	 * @return 实际坐标
	 */
	public static Point addPosition(Point relative)
	{
		return new Point(origin.x + relative.x, origin.y + relative.y);
	}

	/**
	 * 获取相对坐标的实际坐标
	 * 
	 * @param x
	 *            相对坐标的x坐标
	 * @param y
	 *            相对坐标的y坐标
	 * @return 实际坐标
	 */
	public static Point addPosition(int x, int y)
	{
		return new Point(origin.x + x, origin.y + y);
	}

	/**
	 * 鼠标移动到指定位置(相对坐标)
	 * 
	 * @param x
	 * @param y
	 */
	public static void mouseMove(int x, int y)
	{
		robot.mouseMove(origin.x + x, origin.y + y);
	}

	/**
	 * 鼠标移动到指定位置(相对坐标)
	 * 
	 * @param position
	 */
	public static void mouseMove(Point position)
	{
		robot.mouseMove(origin.x + position.x, origin.y + position.y);
		System.out.println();
	}
}
