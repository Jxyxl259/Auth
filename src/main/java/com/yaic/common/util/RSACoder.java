package com.yaic.common.util;

import com.yaic.servicelayer.charset.StandardCharset;
import com.yaic.servicelayer.codec.digest.RSAHelper;

public class RSACoder extends Coder {

	/**
	 * 用私钥对信息生成数字签名
	 *
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return 数字签名
	 * @throws Exception
	 *             异常
	 */
	public static String sign(String dataJson, String privateKey) throws Exception {
		
		byte[] data =hexStringToByte(dataJson);

		return encryptBASE64(RSAHelper.signByMD5withRSA(data, privateKey));
	}

	/**
	 * 校验数字签名
	 *
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 *             异常
	 */
	public static boolean verify(String dataJson, String publicKey, String sign) throws Exception {
		byte[] data = hexStringToByte(dataJson);
		
		return RSAHelper.verifyByMD5withRSA(data, publicKey, decryptBASE64(sign));
	}

	/**
	 * 解密<br>
	 * 用私钥解密
	 *
	 * @param data
	 *            解密数据
	 * @param key
	 *            私钥
	 * @return 解密结果
	 * @throws Exception
	 *             异常
	 */
	public static String decryptByPrivateKey(String dataJson, String key) throws Exception {

		byte[] data = hexStringToByte(dataJson);
		
		return RSAHelper.decryptByPrivateKey(data, key);
	}

	/**
	 * 解密<br>
	 * 用公钥解密
	 *
	 * @param data
	 *            加密数据
	 * @param key
	 *            公钥
	 * @return 解密后数据
	 * @throws Exception
	 *             异常
	 */
	public static String decryptByPublicKey(String dataJson, String key) throws Exception {

		byte[] data = hexStringToByte(dataJson);
		
		return RSAHelper.decryptByPublicKey(data, key);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 *
	 * @param data
	 *            待加密数据
	 * @param key
	 *            公钥
	 * @return 加密后数据
	 * @throws Exception
	 *             异常
	 */
	public static String encryptByPublicKey(String dataJson, String key) throws Exception {

		byte[] data = dataJson.getBytes(StandardCharset.UTF_8);
		
		return byteToHexString(RSAHelper.encryptByPublicKey(data, key));
	}

	/**
	 * 加密<br>
	 * 用私钥加密
	 *
	 * @param data
	 *            待加密数据
	 * @param key
	 *            私钥
	 * @return 加密后数据
	 * @throws Exception
	 *             异常
	 */
	public static String encryptByPrivateKey(String dataJson, String key) throws Exception {

		byte[] data = dataJson.getBytes(StandardCharset.UTF_8);
		
		return byteToHexString(RSAHelper.encryptByPrivateKey(data, key));
	}

	/**
	 * 二进制转16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String strHex = Integer.toHexString(bytes[i]);
			if (strHex.length() > 3) {
				sb.append(strHex.substring(6));
			} else {
				if (strHex.length() < 2) {
					sb.append("0" + strHex);
				} else {
					sb.append(strHex);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 十六进制转二进制
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByte(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				System.out.println("十六进制转byte发生错误！！！");
				e.printStackTrace();
			}
		}
		return baKeyword;
	}

}
