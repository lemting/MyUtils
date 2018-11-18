package hanZiZhuanPinYin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * Java开发汉字转拼音
 * @author Lemting
 * @time 2018-7-23
 * @jar (pinyin4j.jar)
 */

public class PinyinUtils
{
	/**
	 * 获取汉字的拼音
	 * @param str 要转换拼音的汉字
	 * @param isLowerCase: 拼音是否小写
	 * @param jiange: 拼音间的间隔字符
	 * @return 汉字的拼音
	 * @throws Exception 
	 */
	public static String getPinYin(String str,boolean isLowerCase,String jiange) throws Exception
	{
		if(jiange == null || jiange.trim().equals(""))
			jiange = " ";
		char[] charStr =  str.toCharArray();//把字符串转为字符数组
		String[] sss = new String[charStr.length]; //创建一个字符串数组
		HanyuPinyinOutputFormat pof = new HanyuPinyinOutputFormat(); //创建汉语拼音的格式
		pof.setCaseType(isLowerCase?HanyuPinyinCaseType.LOWERCASE:HanyuPinyinCaseType.UPPERCASE); //设置字母大小写
		pof.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //设置拼音的声调
		StringBuffer sb = new StringBuffer(); //用于拼接字符串
		for(int i = 0;i < charStr.length;i++)
		{
			//判断是不是汉字
			if(Character.toString(charStr[i]).matches("[\\u4e00-\\u9fa5]"))
			{
				sss = PinyinHelper.toHanyuPinyinStringArray(charStr[i],pof); //将汉字的多种全拼存放在字符数组中
				sb.append(sss[0]); //取出汉字中读音,放在字符串中
				if(i < charStr.length -1) 
					sb.append(jiange);
			}
			else 
			{
				sb.append(Character.toString(charStr[i]));	//如果不是汉字,直接取出存放到字符串中
				if(i < charStr.length -1)
					sb.append(jiange);
			}
		}	
		return sb.toString();
	}
}