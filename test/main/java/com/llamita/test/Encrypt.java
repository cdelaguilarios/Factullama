package com.llamita.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public static void main(String[] args) {
		
		MessageDigest md = null;
		String password = "admin";
	        try {
	            
	            //SHA-512
//	            md= MessageDigest.getInstance("SHA-512");
//	            md.update(password.getBytes());
	            byte[] mb = md.digest();
//	            System.out.println(Hex.encodeHex(mb));
	            
	            //SHA-1
	            md= MessageDigest.getInstance("SHA-1");
	            md.update(password.getBytes());
	            mb = md.digest();
	            System.out.println(mb);
	            
	            //MD5
//	            md= MessageDigest.getInstance("MD5");
//	            md.update(password.getBytes());
//	            mb = md.digest();
//	            System.out.println(Hex.encodeHex(mb));
	            
	        } catch (NoSuchAlgorithmException e) {
	            //Error
	        }
	    }
	
}
