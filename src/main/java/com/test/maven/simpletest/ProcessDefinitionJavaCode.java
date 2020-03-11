package com.test.maven.simpletest;

import java.util.*;
import java.io.*;

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

public class ProcessDefinitionJavaCode{
/****** START SET/GET METHOD, DO NOT MODIFY *****/
	protected String client_key = "";
	protected String keyID = "";
	protected String data_in = "Hello";
	protected String data_out_enc = "";
	public String getclient_key() {
		return client_key;
	}
	public void setclient_key(String val) {
		client_key = val;
	}
	public String getkeyID() {
		return keyID;
	}
	public void setkeyID(String val) {
		keyID = val;
	}
	public String getdata_in() {
		return data_in;
	}
	public void setdata_in(String val) {
		data_in = val;
	}
	public String getdata_out_enc() {
		return data_out_enc;
	}
	public void setdata_out_enc(String val) {
		data_out_enc = val;
	}
/****** END SET/GET METHOD, DO NOT MODIFY *****/
	public ProcessDefinitionJavaCode() {

	}
	public String invoke() throws Exception {
/* Available Variables: DO NOT MODIFY
	In  : String client_key
	In  : String keyID
	In  : String data_in
	Out : String data_out_enc
* Available Variables: DO NOT MODIFY *****/

	data_out_enc = encrypt(client_key,keyID,data_in);
	return data_out_enc;

}
 	private SecretKey key;
public String encrypt(String client_key, String keyID, String data_in)
	{
	    client_key="8ysij9ygxsIRFGc6s4/0hdsd4c5BWFJ8qIbu3hkQFMg=";
	    byte[] decodedKey = Base64.getDecoder().decode(client_key);
	    System.out.println(decodedKey);
	    System.out.println(client_key);
	    System.out.println(keyID);
  System.out.println(data_in);
	    key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		System.out.println(key);
	    
	    //set kid in along with alogrithm and encryption method
	    //set kid in along with alogrithm and encryption method
	    JWEHeader header =new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).keyID(keyID).build();
	    
	    
	    Payload payload = new Payload(data_in);

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
		
		 System.out.println(jweObject.serialize());
	  return jweObject.serialize();
	 
	}
}