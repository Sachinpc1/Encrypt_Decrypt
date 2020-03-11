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
//import org.forgerock.json.jose.jwk.JWK;
//import org.forgerock.json.jose.jws.JwtSecureHeader;

public class Crypto {
	
	private SecretKey key;
	
	public void encrypt()
	{
		// Generate symmetric 128 bit AES 
	    KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    keyGen.init(256);
	    key = keyGen.generateKey();

	    String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
	    
	    JWEHeader header =new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256).keyID("2ae77552-f8e9-4b98-b7d3-4045cec00f22").build();
	    
	    
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

	    //String enc_str = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiYWM5OWMyNjMtYjY1Zi00NzVkLTllNWUtYzMwMDJiZGYxZDNjIn0..GsvLVhAlihrJFNoGX9rcKw.rAsJQ695G6zsX0L421iSd-el6L4Ganf15smzuHFJKdOOQwNwzx2OO2X-Lr6k4zbVO01WitKRN1OTQ97rcyQVKnza-idrjSc0217Zdfmfj4Y.nDfmAQrbnkaQ001x_Fm_UA";
	    // Parse into JWE object again...
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
		
		 //decode the base64 encoded string
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
	    //assertEquals("Hello world!", payload.toString());
	}

}
