package encoderAndDecoder;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MyAES
{
	//AES需要使用的key
	private static Key convertSecretKey = getKey_bc();
	
	//AES加密
	public static byte[] AESEncoder(String src) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		//设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		return cipher.doFinal(src.getBytes());
	}
	
	//AES解密
	public static byte[] AESDecoder(byte[] result) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		//设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
		return cipher.doFinal(result);
	}
	
	//jdk方式获取key的代码
	public static Key getKey()
	{
		Key key = null;
		try {
			//生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//转换key
			key = new SecretKeySpec(byteKey, "AES");
		}catch (NoSuchAlgorithmException e){e.printStackTrace();}
		return key;
	}
	//Bouncy Castle方式获取key的代码
	public static Key getKey_bc()
	{
		Key key = null;
		try {
			//添加Bouncy Castle的加解密类
			Security.addProvider(new BouncyCastleProvider());
			//生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES","BC"); //设置使用Bouncy Castle的加解密类
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//转换key
			key = new SecretKeySpec(byteKey, "AES");
		}catch (NoSuchAlgorithmException | NoSuchProviderException e){e.printStackTrace();}
		return key;
	}
}
