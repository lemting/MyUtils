package randomNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 获取随机数
 * @author Lemting
 * @time 2018-2-6
 */

public class RandomNumbers
{
	private static Random random = new Random();
	
	/**
	 * 获取指定闭区间[min,max]的随机数
	 * @param min 区间最小值
	 * @param max 区间最大值
	 * @return 闭区间内随机一个数
	 */
	public static int getRandom(int min,int max)
	{
		return random.nextInt(Math.abs(max - min) + 1) + Math.min(min, max);
	}
	
	/**
	 * 获取闭区间[min,max]间指定个数的不重复的随机数
	 * @param min 区间最小值
	 * @param max 区间最大值
	 * @param num 获取个数
	 * @param isRepeatable 是否可以重复
	 * @return 闭区间内的随机数的List集合
	 */
	public static List<Integer> getRandomNumbersList(int min,int max,int num,boolean isRepeatable)
	{
		List<Integer> list = new ArrayList<>();	
		if(Math.abs(max - min) + 1 >= num && num > 0)	//若区间内没有指定个数的数,或个数不为正数,则不获取随机数
		{
			if(max - min + 1 == num)	//若区间刚好有这么多的数,则无需进行随机获取
				for(int i = min;i <= max;i++)
					list.add(i);
			else
			{	
				while(num != list.size())	//随机获取直到指定个数
				{
					int random = getRandom(min,max);
					if(isRepeatable || !list.contains(random))	//判断是否可以添加该次获取的随机数
						list.add(random);
				}
			}
		}
		if(!list.isEmpty())
			Collections.sort(list);
		return list;
	}
}
