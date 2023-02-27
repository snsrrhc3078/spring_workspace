package com.edu.springshop.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.edu.springshop.exception.HashException;

/*
 	평문을 해시값으로 변경(개발자가 알고리즘 선택 가능)
 */
public class PassConverter {
	public String convertHash(String text) throws HashException{
		StringBuilder result = new StringBuilder();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//일반 스트링 쪼개기
			
			byte[] hash = digest.digest(text.getBytes("UTF-8"));
			
			for(int i =0;i<hash.length;i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length()==1) result.append("0");
				result.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new HashException("암호화 실패", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new HashException("암호화 실패", e);
		}
		
		return result.toString();
	}
	
	
//	public static void main(String[] args) {
//		String a = PassConverter.convertHash("asdf");
//		System.out.println(a);
//	}
}
