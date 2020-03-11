package com.test.maven.simpletest;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;

public class cryptoTempOld {
	
	private SecretKey key;
	
	public void encrypt()
	{

	    //encode the key
	    String encodedKey = "b74AiMwHo+rbdH0lJsSeSMA0Dgo0aD0i/peg70A0ywg=";
	    
	    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	    
	    key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    
	    //set kid in along with alogrithm and encryption method
	    JWEHeader header =new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256).keyID("d8dd52ca-623f-477a-be66-cd6af7fb9b5c").build();
	    
	    
	    Payload payload = new Payload("Well, as of this moment, they're on DOUBLE SECRET PROBATION!");

	    // Create the JWE object and encrypt it
	    JWEObject jweObject = new JWEObject(header, payload);
	    try {
			jweObject.encrypt(new DirectEncrypter(key));
		} catch (KeyLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Serialise to compact JOSE form...
	    String jweString = jweObject.serialize();

	    System.out.println("jwestring = "+jweString);
	    try {
			jweObject = JWEObject.parse(jweString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    System.out.println("Encrypted Data: "+jweObject.getParsedString());
	    decrypt(encodedKey,jweString);
	}
	
	public void decrypt(String encodedKey, String encrypted_data)
	{
		 Payload payload;
		
		 //decode the base64 encoded key
	     byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	     //rebuild key using SecretKeySpec
	     SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		
	     JWEObject jweObject = null;
		 try {
				jweObject = JWEObject.parse(encrypted_data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    // Decrypt
	    try {
			jweObject.decrypt(new DirectDecrypter(key));
		} catch (KeyLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Get the plain text
	    payload = jweObject.getPayload();
	    System.out.println("Decrypted data: " +payload.toString());
	}

}
