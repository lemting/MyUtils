package Test;

import org.apache.commons.codec.binary.Hex;
import encoderAndDecoder.My3DES;
//import erWeiMa.ErWeiMa;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		//ErWeiMa.create("D:\\erweima.png", "怪物猎人世界!!!", "png", 4, 2, 300, 300, 2);
		//String src = "E:\\Tencent\\Tencent Files\\1373107380\\FileRecv\\erweima_1.jpg";
		//ErWeiMa.jieXi(src);
		String src = "imooc security 3des";
		byte[] result = My3DES.DESedeEncoder(src);
		String s = Hex.encodeHexString(result);
		System.out.println("encoder: [" + src + "] --> [" + s + "]");
		System.out.println("decoder: [" + s + "] --> [" + new String(My3DES.DESedeDecoder(result)) + "]");
	}
}
