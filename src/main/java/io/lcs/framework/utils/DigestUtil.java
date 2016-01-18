package io.lcs.framework.utils;

import org.springframework.util.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密/编码工具
 * Created by lcs on 9/13/15.
 */
public class DigestUtil {
	private final static String ALPHABET = "asdfghjklpoiuytrewqzxcvbnm=-0987654321_+ASDFGHJKLPOIUYTREWQZXCVBNM";
	private final static String NUM_ALPHABET = "0987654321";

	public static String digestString(String pass, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest md;
		ByteArrayOutputStream bos;
		md = MessageDigest.getInstance(algorithm);
		byte[] digest = md.digest(pass.getBytes());
		return new String(digest);
	}

	public static String MD5(String string) {
		return MD5(string.getBytes());
	}

	public static String MD5(byte[] bytes) {
		return DigestUtils.md5DigestAsHex(bytes).toUpperCase();
	}

	public static String randomStr(int length) {
		return random(length, ALPHABET);
	}

	public static String randomNumStr(int length) {
		return random(length, NUM_ALPHABET);
	}

	private static String random(int length, String data) {
		String str = "";
		int len = data.length();
		while (length > 0) {
			length--;
			str += data.charAt((int) (Math.random() * len));
		}
		return str;
	}
}
