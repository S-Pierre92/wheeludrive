package com.wheeludrive2.tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.wheeludrive2.exception.WheelUDriveException;

public class EncryptionUtils {

	private final static Logger log = Logger.getLogger(EncryptionUtils.class);

	private static SecretKeySpec secretKey;
	private static byte[] key;

	private static void setKey(String myKey) throws WheelUDriveException {
		try {
			key = myKey.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			throw new WheelUDriveException("Impossible to set key for decryption or incryption ", e);
		}

	}

	public static String encrypt(String strToEncrypt, String secret) throws WheelUDriveException {

		try {
			log.info("Testing llog4j");
			setKey(secret);
			Cipher cipher;
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			log.error(e.getMessage());
			throw new WheelUDriveException("Impossible to encrypt \"" + strToEncrypt + "\"", e);
		}

	}

	public static String decrypt(String strToDecrypt, String secret) throws WheelUDriveException {

		try {
			setKey(secret);
			Cipher cipher;
			cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			log.error(e.getMessage());
			throw new WheelUDriveException("Impossible to decrypt \"" + strToDecrypt + "\"", e);
		}

	}
}