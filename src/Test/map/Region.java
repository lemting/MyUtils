package Test.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Region
{
	private String name;	//地区名称
	private Point position = new Point();	//地区相对坐标
	private List<Point> regions = new ArrayList<>(); //地区关卡集合
	
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Point getPosition(){return position;}
	public Point getRelativePosition(){return new Point(position.x,position.y);}
	public void setPosition(Point position){this.position = new Point(position.x,position.y);}
	public void setPosition(int x,int y){position = new Point(x, y);}
	public List<Point> getRegions(){return regions;}
	public void setRegions(List<Point> regions){this.regions = regions;}
}
