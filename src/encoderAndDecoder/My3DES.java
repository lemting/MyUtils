package encoderAndDecoder;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class My3DES
{
	//3DES需要使用的key
	private static Key convertSecretKey = getKey();
	
	//3DES加密
	public static byte[] DESedeEncoder(String src) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		//设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		return cipher.doFinal(src.getBytes());
	}
	
	//3DES解密
	public static byte[] DESedeDecoder(byte[] result) throws Exception
	{
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
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
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(new SecureRandom());	//keyGenerator.init(168);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//key转换
			DESedeKeySpec desedeKeySpec = new DESedeKeySpec(byteKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			convertSecretKey = factory.generateSecret(desedeKeySpec);
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
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");//设置使用Bouncy Castle的加解密类
			keyGenerator.init(new SecureRandom());	//keyGenerator.init(168);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteKey = secretKey.getEncoded();
			//key转换
			DESedeKeySpec desedeKeySpec = new DESedeKeySpec(byteKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			convertSecretKey = factory.generateSecret(desedeKeySpec);
		}catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchProviderException e){e.printStackTrace();}	
		return convertSecretKey;
	}
}
