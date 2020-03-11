package com.test.maven.simpletest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

//import java.security.NoSuchAlgorithmException;
//import java.text.ParseException;
//import java.util.Base64;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//import java.lang.*;
//
//import org.jose4j.jwa.AlgorithmConstraints;
//import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
//import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
//import org.jose4j.jwe.JsonWebEncryption;
//import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
//import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.*;


public class App 
{
	public static void main(String[] args) throws Exception {
		
//		String pl = "{\"addresses\":[],\"dob\":\"1997-12-03\",\"email_ids\":[\"shikhar.jain@idfy.com\"],\"ind_aadhaar_xml_details\":{\"document_1\":\"https://storage.googleapis.com\"},\"ind_pan_details\":{\"dob\":\"1990-04-15\",\"id_number\":\"ASDASDAAS\",\"name\":{\"first_name\":\"Shikhar\",\"last_name\":\"Jain\"}},\"mobile_numbers\":[],\"name\":{\"first_name\":\"Shikhar \",\"last_name\":\"Jain\"},\"wet_signatures\":[\"https://in.bmscdn.com/iedb/artist/images/website/poster/large/shah-rukh-khan-2092-12-09-2017-02-10-43.jpg\"]}";
		
		String encodedKey = "8ysij9ygxsIRFGc6s4/0hdsd4c5BWFJ8qIbu3hkQFMg=";
//		String KeyId = "0e979";
//		String plain_text_payload ="Well, as of this moment, they're on DOUBLE SECRET PROBATION!";
		cryptoTemp x = new cryptoTemp();
		
	
//		String res = x.encrypt(encodedKey,KeyId,pl);
//		System.out.println(res);
		
		ProcessDefinitionJavaCode nen = new ProcessDefinitionJavaCode();
		String res = nen.invoke();
		System.out.println("PDJ encrypted value  = :"+res);
//		String v = "eyJraWQiOOGRkNTJjYS02MjNmLTQ3N2EtYmU2Ni1jZDZhZjdmYjliNWMiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..E9wM9IQJBPlJm3EC.sZAVh4AA_KLzV2wriJ-Z4rQ5xKzX-KqY3vrKwETtjowohF_p5Uz1rytyY-Zi-jZjFK7dSxhMuca2vdNz.W9UNLr3YusAbymUCXRInPA";
//		JWEObject jweObject = new JWEObject();
//		
//		try {
//			jweObject = JWEObject.parse(jweString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	    
//	    System.out.println("Encrypted Data: "+jweObject.getParsedString());
//	    String res = decrypt(encodedKey,jweString);
		String fin = x.decrypt(encodedKey, res);
		System.out.println("The Decrypted text is :"+fin);
		
	

}
}
