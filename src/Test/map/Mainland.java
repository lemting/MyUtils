package Test.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图: 大陆
 * @author Lemting
 */
public class Mainland
{
	private String name;	//大陆名称
	private Point position = new Point();	//大陆相对坐标
	private List<Region> regions = new ArrayList<>(); //大陆地区集合
	
	public Mainland(){}
	
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Point getPosition(){return position;}
	public Point getRelativePosition(){return new Point(position.x,position.y);}
	public void setPosition(Point position){this.position = new Point(position.x,position.y);}
	public void setPosition(int x,int y){position = new Point(x,y);}
	public List<Region> getRegions(){return regions;}
	public void setRegions(List<Region> regions){this.regions = regions;}
}
