package be.dashmon.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.stereotype.Component;

@Component
public class HexCoder {
	
	Cipher ecipher;
    Cipher dcipher;
    // 8-byte Salt
    byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    int iterationCount = 19;
    
    
    
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM = "0123456789";
	private static final String SPL_CHARS = "!@#$%^&*_=+-/";
	
	
	 public static char[] generatePswd(int minLen, int maxLen, int noOfCAPSAlpha, 
	            int noOfDigits, int noOfSplChars) {
	        if(minLen > maxLen)
	            throw new IllegalArgumentException("Min. Length > Max. Length!");
	        if( (noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen )
	            throw new IllegalArgumentException
	            ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
	        Random rnd = new Random();
	        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
	        char[] pswd = new char[len];
	        int index = 0;
	        for (int i = 0; i < noOfCAPSAlpha; i++) {
	            index = getNextIndex(rnd, len, pswd);
	            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
	        }
	        for (int i = 0; i < noOfDigits; i++) {
	            index = getNextIndex(rnd, len, pswd);
	            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
	        }
	        for (int i = 0; i < noOfSplChars; i++) {
	            index = getNextIndex(rnd, len, pswd);
	            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
	        }
	        for(int i = 0; i < len; i++) {
	            if(pswd[i] == 0) {
	                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
	            }
	        }
	        return pswd;
	    }
	     
	    private static int getNextIndex(Random rnd, int len, char[] pswd) {
	        int index = rnd.nextInt(len);
	        while(pswd[index = rnd.nextInt(len)] != 0);
	        return index;
	    }
	    
//    public CryptoUtil() 
//    { 

//    }
	public String encrypt(String secretKey, String plainText) 
            throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        //Key generation for enc and desc
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

        //Enc process
        ecipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
        String charSet="UTF-8";       
        byte[] in = plainText.getBytes(charSet);
        byte[] out = ecipher.doFinal(in);
        String encStr=new sun.misc.BASE64Encoder().encode(out);
        return encStr;
    }
     /**     
     * @param secretKey Key used to decrypt data
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     */
    public String decrypt(String secretKey, String encryptedText)
     throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
         //Key generation for enc and desc
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        //Decryption process; same key will be used for decr
        dcipher=Cipher.getInstance(key.getAlgorithm());
        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
        byte[] utf8 = dcipher.doFinal(enc);
        String charSet="UTF-8";     
        String plainStr = new String(utf8, charSet);
        return plainStr;
    } 
	

//    public static void main(String[] args) throws Exception {
//        CryptoUtil cryptoUtil=new CryptoUtil();
//        String key="ezeon8547";   
//        String plain="This is an important message";
//        String enc=cryptoUtil.encrypt(key, plain);
//        System.out.println("Original text: "+plain);
//        System.out.println("Encrypted text: "+enc);
//        String plainAfter=cryptoUtil.decrypt(key, enc);
//        System.out.println("Original text after decryption: "+plainAfter);
//    }    
	
}
