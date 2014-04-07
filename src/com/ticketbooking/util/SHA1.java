package com.ticketbooking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
	
	public static String SHA(String key){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(key.getBytes());
			key = converToString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	private static String converToString(byte[] digest){
		String key = "";
		String tmpStr = "";
		for(int i=0;i<digest.length;i++){
			tmpStr = (Integer.toHexString(digest[i] & 0xff));
			if(tmpStr.length() == 1){
				key += "0" + tmpStr;
            }
            else{
            	key += tmpStr;
            }
		}
		return key;
	}
}
