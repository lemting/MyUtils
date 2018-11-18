package randomPassword;

import java.util.Random;

/**
 * 用于获取随机密码
 * @author Lemting
 * @time 2018-2-6
 */

public class randomPassword
{
	/**
	 * 随机生成密码(第一个字符为字母)
	 * @param minLen 	密码的最小字符长度
	 * @param maxLen 	密码的最大字符长度
	 * @param number  	密码是否可以有数字
	 * @param teshuzifu 密码可以使用的特殊字符(eg: "%&+/-*=")
	 * @return 随机密码
	 */
	public static String createPasswords(int minLen,int maxLen,boolean number,String teshuzifu)
	{
		if(minLen > maxLen)
		{
			maxLen += minLen;
			minLen = maxLen - minLen;
			maxLen -= minLen;
		}
		Random rd = new Random();
		StringBuffer sb = new StringBuffer();
		int first = rd.nextInt(26);//第一个字符是随机字母
		sb.append((char)((rd.nextBoolean() ? 'a' : 'A') + first));
		int len = rd.nextInt(maxLen - minLen + 1) + minLen - 1; //随机生成密码的长度( = 最终随机生成的密码长度 - 1)
		//随机生成字母,数字,特殊字符范围: 
		boolean flag = teshuzifu != null && !teshuzifu.equals("");
		int i = 0;
		while(i < len)
		{
			char c = '\0';
			//判断随机生成的是字母,数字还是特殊字符
			switch (rd.nextInt(5))
			{
				case 0: case 1: case 2: c = (char)(rd.nextInt(26) + (rd.nextBoolean() ? 'a':'A')); break;
				case 3: if(number){c = (char)(rd.nextInt(10) + '0');} break;
				case 4: if(flag){c = teshuzifu.charAt(rd.nextInt(teshuzifu.length()));}//随机获取特殊字符中的一个字符
			}
			if(c == '\0')
				continue;
			sb.append(c);i++;
		}
		return sb.toString();		
	}
}
