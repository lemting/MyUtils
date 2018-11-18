package encoderAndDecoder;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 自定义Base64加解密类,只支持加解密字符串
 * @author Lemting
 * @time 2018-10-28
 * @jar (bcprov-ext-jdk15on-149.jar,bcprov-jdk15on-149.jar) (commons-codec.jar)
 */

public class MyBase64
{
	
	/***
	 * jdk自带Base64加密
	 * @param src 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String jdkBase64Encoder(String src)
	{
		//获取加密对象
		Encoder encoder = Base64.getEncoder();
		//加密并返回
		return encoder.encodeToString(src.getBytes());
	}
	
	/***
	 * jdk自带Base64解密
	 * @param src 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String jdkBase64Decoder(String src)
	{
		//获取解密对象
		Decoder decoder = Base64.getDecoder();	
		//
		byte[] srcDecoder = decoder.decode(src);
		return new String(srcDecoder);
	}
	
	/***
	 * Commons Codec的Base64加密(需要导入  bcprov-ext-jdk15on-149.jar,bcprov-jdk15on-149.jar)
	 * @param src 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String commonsCodecBase64Encoder(String src)
	{
		//根据静态方法直接加密
		byte[] srcEncoder = org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
		return new String(srcEncoder);
	}
	
	/***
	 * Commons Codec的Base64解密(需要导入  bcprov-ext-jdk15on-149.jar,bcprov-jdk15on-149.jar)
	 * @param src 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String commonsCodecBase64Decoder(String src)
	{
		//根据静态方法直接解密
		byte[] srcEncoder = org.apache.commons.codec.binary.Base64.decodeBase64(src.getBytes());
		return new String(srcEncoder);
	}
	
	/***
	 * Bouncy Castle的Base64加密(需要导入  commons-codec.jar)
	 * @param src 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String bouncyCastleBase64Encoder(String src)
	{
		//根据静态方法直接解密
		byte[] srcEncoder = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		return new String(srcEncoder);
	}
	
	/***
	 * Bouncy Castle的Base64解密(需要导入  commons-codec.jar)
	 * @param src 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String bouncyCastleBase64Decoder(String src)
	{
		//根据静态方法直接解密
		byte[] srcEncoder = org.bouncycastle.util.encoders.Base64.decode(src);
		return new String(srcEncoder);
	}
}
