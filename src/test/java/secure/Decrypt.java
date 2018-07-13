package secure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {

	public static void main(String[] args) {
		try {
			
			decodeFile();
	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void decodeFile() {
        try {
//            String myPwdEncode = "giuseppegiuseppe";
//            SecretKeySpec yourKey = new SecretKeySpec(myPwdEncode.getBytes(), "AES");
//            byte[] fileData = readFile();
//            
//            byte[] data = yourKey.getEncoded();
//            SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length, "AES/ECB/PKCS5Padding");
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//
//            byte[] decodedData = cipher.doFinal(fileData);
//            
//
//            String str = new String(decodedData);
//            System.out.println("DECODED FILE CONTENTS : " + str);
        	
        	
        	byte[] fileData = readFile();
        	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();
            
//        	IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
//            SecretKeySpec spec = new SecretKeySpec(key.getEncoded(), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, spec, iv);
            
            
            SecretKeySpec spec = new SecretKeySpec(key.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//AES/ECB/PKCS5Padding		AES/CBC/PKCS5Padding
            cipher.init(Cipher.DECRYPT_MODE, spec);

            byte[] decodedData = cipher.doFinal(fileData);
            String str = new String(decodedData);
            System.out.println("DECODED FILE CONTENTS : " + str);
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static byte[] readFile() {
        byte[] contents = null;

        String encryptedFileName = "memory.mem";
        File file = new File("c:" + File.separator +encryptedFileName);

        //File file = new File(Environment.getExternalStorageDirectory() + File.separator, encryptedFileName);
        int size = (int) file.length();
        contents = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            try {
                buf.read(contents);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }
    


}
