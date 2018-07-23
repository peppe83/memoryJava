package secure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
	//static String encryptedFileName = "latlng.mem";
	static String encryptedFileName = "latitude.mem";
	static String myPwdEncode = "giuseppegiuseppe";
	
	public static void main(String[] args) {
		try {
			
            SecretKeySpec yourKey = new SecretKeySpec(myPwdEncode.getBytes(), "AES");
            byte[] decodedData = decodeFile(yourKey, readFile());
            String str = new String(decodedData);
            System.out.println("DECODED FILE CONTENTS: <<" + str + ">>");
		} catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
			throw new RuntimeException( e );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static byte[] decodeFile(SecretKey yourKey, byte[] fileData) throws Exception {
    	byte[] data = yourKey.getEncoded();
//        SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length, "AES/ECB/PKCS5Padding");
    	SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);
        return decrypted;
    }
    
    public static byte[] readFile() {
        byte[] contents = null;

        File file = new File("e:" + File.separator + "DevApps" + File.separator + "memory" + File.separator + encryptedFileName);

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
