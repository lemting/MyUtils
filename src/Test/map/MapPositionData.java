package Test.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Test.map.Mainland;
import Test.map.Region;

public class MapPositionData
{
	private static final List<Mainland> mainlands = new ArrayList<>();
	
	public static Point getChaPoint(){return new Point(1232,42);}
	public static Point getPrevPoint(){return new Point(32,360);}
	public static Point getNextPoint(){return new Point(1250,360);}
	
	/**
	 * 获取地图坐标,大主宰 (720P)环境下
	 * @param origin 坐标原点(游戏左上角坐标)
	 * @return 地图坐标
	 */
	public static List<Mainland> getMapPositionData()
	{
		mainlands.add(createTianLuoMap());	//天罗大陆
		//mainlands.add(createPoSuiMap());	//破碎大陆
		//mainlands.add(createbeiCangMap());	//北仓大陆
		//mainlands.add(createbeiLingMap());	//北灵境
		return mainlands;
	}
	
	//天罗大陆地图
	public static Mainland createTianLuoMap()
	{
		Mainland tianLuoMap = new Mainland();	
		List<Region> regions = new ArrayList<>();
		List<Point> temp;
		Region daLuo = new Region();Region jinChi = new Region();Region daLuoJinChi = new Region();
		Region daLuoJinTai = new Region();Region leiMo = new Region();Region baiGu = new Region();
		Region longFeng = new Region();Region chiHong = new Region();Region siWang = new Region();
		Region anHei = new Region();
		daLuo.setName("大罗峰");
		daLuo.setPosition(410,225);
		temp = daLuo.getRegions();
		temp.add(new Point(200,215));temp.add(new Point(325,265));temp.add(new Point(415,480));temp.add(new Point(580,505));
		temp.add(new Point(800,505));temp.add(new Point(1065,470));temp.add(new Point(915,395));temp.add(new Point(710,325));
		temp.add(new Point(600,215));temp.add(new Point(825,230));
		daLuo.setRegions(temp);
		
		jinChi.setName("金赤峰");
		jinChi.setPosition(200,315);
		temp = jinChi.getRegions();
		temp.add(new Point(840,505));temp.add(new Point(585,485));temp.add(new Point(355,465));temp.add(new Point(200,350));
		temp.add(new Point(325,250));temp.add(new Point(490,215));temp.add(new Point(665,195));temp.add(new Point(815,250));
		temp.add(new Point(950,370));temp.add(new Point(1080,235));
		jinChi.setRegions(temp);
		
		daLuoJinChi.setName("大罗金池");
		daLuoJinChi.setPosition(320,480);
		temp = daLuoJinChi.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		daLuoJinChi.setRegions(temp);
		
		daLuoJinTai.setName("大罗金台");
		daLuoJinTai.setPosition(385,550);
		temp = daLuoJinTai.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		daLuoJinTai.setRegions(temp);
		
		leiMo.setName("雷魔塔");
		leiMo.setPosition(605,510);
		temp = leiMo.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		leiMo.setRegions(temp);
		
		baiGu.setName("白骨山");
		baiGu.setPosition(680,405);
		temp = baiGu.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		baiGu.setRegions(temp);
		
		longFeng.setName("龙凤梯");
		longFeng.setPosition(700,270);
		temp = longFeng.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		longFeng.setRegions(temp);
		
		chiHong.setName("赤红山谷");
		chiHong.setPosition(930,230);
		temp = chiHong.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		chiHong.setRegions(temp);
		
		siWang.setName("死亡遗迹");
		siWang.setPosition(1030,310);
		temp = siWang.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		siWang.setRegions(temp);
		
		anHei.setName("暗黑平原");
		anHei.setPosition(960,470);
		temp = anHei.getRegions();
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));temp.add(new Point(0,0));
		temp.add(new Point(0,0));temp.add(new Point(0,0));
		anHei.setRegions(temp);
		
		regions.add(daLuo);regions.add(jinChi);regions.add(daLuoJinChi);regions.add(daLuoJinTai);regions.add(leiMo);
		regions.add(baiGu);regions.add(longFeng);regions.add(chiHong);regions.add(siWang);regions.add(anHei);
		tianLuoMap.setName("天罗大陆");
		tianLuoMap.setPosition(972,28);
		tianLuoMap.setRegions(regions);
		return tianLuoMap;
	}
	//破碎大陆地图
	public static Mainland createPoSuiMap()
	{
		Mainland poSuiMap = new Mainland();	
		return poSuiMap;
	}
	//北仓大陆地图
	public static Mainland createbeiCangMap()
	{
		Mainland beiCangMap = new Mainland();	
		return beiCangMap;
	}
	//北灵境地图
	public static Mainland createbeiLingMap()
	{
		Mainland beiLingMap = new Mainland();	
		return beiLingMap;
	}
}
