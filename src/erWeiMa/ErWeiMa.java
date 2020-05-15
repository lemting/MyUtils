package erWeiMa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码的生成和解析
 * @author Lemting
 * @time 2018-7-20
 * @jar (zxing.jar)
 */

public class ErWeiMa
{
	/**
	 * 生成二维码
	 * @param path: 生成的二维码路径
	 * @param data: 二维码数据
	 * @param format: 二维码后缀(默认png)
	 * @param version: 二维码版本(默认2)
	 * @param level: 纠错等级(1: L,2: M(默认),3: Q,4: H)
	 * @param width: 二维码宽度(默认300)
	 * @param height: 二维码高度(默认300)
	 * @param magin: 二维码外边距(可以为0,默认2)
	 */
	public static void create(String path,String data,String format,int version,int level,int width,int height,int magin)
	{
		format = format.trim().toLowerCase();
		//二维码格式
		switch (format)
		{
			case "jpg":	case "bmp": break;
			case "png": default: format = "png";
		}
		Object errorCorrectionLevel = null; //纠错等级
		switch (level)
		{
			case 1: errorCorrectionLevel = ErrorCorrectionLevel.L;	break;
			case 3: errorCorrectionLevel = ErrorCorrectionLevel.Q;	break;
			case 4: errorCorrectionLevel = ErrorCorrectionLevel.H;	break;
			case 2: default: errorCorrectionLevel = ErrorCorrectionLevel.M;	//默认M级
		}
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");	//设置字符编码
		hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel); //设置纠错等级
		hints.put(EncodeHintType.MARGIN, (magin >= 0)?magin:2);	 //设置外边距
		hints.put(EncodeHintType.QR_VERSION,(version>40||version<1)?2:version);
		try{
			//获取BitMatrix对象
			BitMatrix bitMatrix = new MultiFormatWriter().encode(data,BarcodeFormat.QR_CODE,width<=0?300:width,height<=0?300:height,hints);
			MatrixToImageWriter.writeToPath(bitMatrix,format,new File(path).toPath()); //生成二维码
		}catch (WriterException | IOException e){e.printStackTrace();} 
	}
	
	/**
	 * 解析二维码(可能会解析不了H级)
	 * @param path: 二维码图片路径
	 */
	public static void jieXi(String path)
	{
		MultiFormatReader formatReader = new MultiFormatReader();
		Result result = null;
		try{
			BufferedImage image = ImageIO.read(new File(path));
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			//参数设置
			Map<DecodeHintType,Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			result = formatReader.decode(binaryBitmap,hints);
		} catch (NotFoundException | IOException e){System.out.println("解析失败!!!");e.printStackTrace();}
		if(result != null)
		{
			System.out.println(result.toString()); //输出结果
			System.out.println("二维码格式类型: " + result.getBarcodeFormat());
			System.out.println("二维码文本内容: " + result.getText());
		}
	}
}
