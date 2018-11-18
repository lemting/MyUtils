package bianMa;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符编码相关操作
 * @author hasee
 * @time 2017-7-20
 */

public class BianMaZhuanHuan
{
	/**
	 * 读取指定编码的文件
	 * @param path 文件路径
	 * @param ecoding 文件编码
	 */
	public static void readEcoding(String path,String ecoding)
	{
		InputStreamReader isr = null;
		char[] chs = new char[1024];
		int len = 0;
		try{
			isr = new InputStreamReader(new FileInputStream(path),ecoding);
			while((len = isr.read(chs)) != -1)
				System.out.print(new String(chs,0,len));
		}catch (IOException e){e.printStackTrace();}
	}
	
}
