package hangLieShi;

import java.util.Scanner;

public class HangLieShi
{
	//代数余子式的系数
	public int getXiShu(int j)
	{
		return (j % 2 == 0) ? 1 : -1;
	}
	
	//递归计算行列式结果
	public int dis(int[][] arr,int j)
	{
		if(arr.length == 1)
			return arr[0][0];
		else if(arr.length == 2)
			return arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
		int[][] array = null;
		int n = arr.length - 1;
		if(j == -1)
		{
			array = arr;
			n++;
		}
		else
		{
			array = new int[n][n];
			for(int x = 0;x < n;x++)
				for(int y = 0;y < n;y++)
				{
					if(y < j)
						array[x][y] = arr[x + 1][y];
					else if(y < n)
						array[x][y] = arr[x + 1][y + 1];
				}
			if(array.length == 2)
				return array[0][0] * array[1][1] - array[0][1] * array[1][0];
		}
		int sum = 0;
		for(int y = 0;y < n;y++)
			sum += array[0][y] * getXiShu(y) * dis(array, y);
		return sum;
	}
	
	//获取输入行列式
	@SuppressWarnings("resource")
	public int[][] inputArr()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入行列式阶数: ");
		String sn = sc.nextLine();
		int n = Integer.parseInt(sn.trim());
		int arr[][] = new int[n][n];
		for(int i = 0;i < n;i++)
		{
			System.out.print("请输入  " + n + " 阶行列式第  " + (i + 1) + " 行: ");
			String str = sc.nextLine();
			String[] strs = str.split(",|\\s");
			for(int j = 0;j < n && j < strs.length;j++)
				arr[i][j] = Integer.parseInt(strs[j].trim());
		}
		return arr;
	}
	
	//计算行列式入口
	public void start()
	{
		int[][] arr = inputArr();
		long l1 = System.currentTimeMillis();
		for(int i = 0;i < arr.length;i++)
		{
			System.out.print("|");
			for(int j = 0;j < arr.length;j++)
			{
				System.out.print(arr[i][j]);
				if(j < arr.length - 1)
					System.out.printf("\t");
			}
			System.out.print("|");
			if(i == arr.length / 2)
				System.out.print(" = " + dis(arr, -1));
			System.out.println();
		}
		System.out.println("time: " + (System.currentTimeMillis() - l1) + " ms");
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		HangLieShi h = new HangLieShi();
		while(true)
		{
			h.start();
			new Scanner(System.in).nextLine();
		}
	}
}
