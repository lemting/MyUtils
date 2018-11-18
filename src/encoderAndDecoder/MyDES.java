package encoderAndDecoder;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MyDES
{
	//DES需要使用的key
	private static Key convertSecretKey = getKey();
	
	//DES加密
	public static byte[] jdkDESEncoder(String src) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		//设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		return cipher.doFinal(src.getBytes());
	}
	
	//DES解密
	public static byte[] jdkDESDecoder(byte[] result) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		//设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
		return cipher.doFinal(result);
	}
	
	//jdk方式获取key的代码
	public static Key getKey()
	{
		Key convertSecretKey = null;
		try {
			//生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56); //设置密钥长度
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//key转换
			DESKeySpec desKeySpec = new DESKeySpec(byteKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			convertSecretKey = factory.generateSecret(desKeySpec);
		}catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e){e.printStackTrace();}	
		return convertSecretKey;
	}
	
	//Bouncy Castle方式获取key的代码
	public static Key getKey_bc()
	{
		Key convertSecretKey = null;
		try {
			//添加Bouncy Castle的加解密类
			Security.addProvider(new BouncyCastleProvider());
			//生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");//设置使用Bouncy Castle的加解密类
			keyGenerator.init(56); //设置密钥长度
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//key转换
			DESKeySpec desKeySpec = new DESKeySpec(byteKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			convertSecretKey = factory.generateSecret(desKeySpec);
		}catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchProviderException e){e.printStackTrace();}	
		return convertSecretKey;
	}
}
