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

public class cryptoTemp {
	
	private SecretKey key;
	
	public String encrypt(String encodedKey, String keyID, String plain_text_payload)
	{
	    
	    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	    
	    key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    
	    //set kid in along with alogrithm and encryption method
	    JWEHeader header =new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).keyID(keyID).build();
	    
	    
	    Payload payload = new Payload(plain_text_payload);

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
	  return jweObject.serialize();
	}
	
	public String decrypt(String encodedKey, String encrypted_data)
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
	    return payload.toString();
	}

}