package com.gma.assistance.gma.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AESEncodeUtil {
    //Initial vector (offset)
    //Encoding
    public static final String bm = "UTF-8";
    public static final String VIPARA = "aabbccddeeffgghh"; //AES is 16bytes. DES is 8bytes

    //private key (key)
    private static final String ASE_KEY = "aabbccddeeffgghh"; //The DES fixed format is 128 bits, which is 8 bytes.

    /**
     * Encryption
     *
     * @param cleartext string before encryption
     * @return encrypted string
     */
    public static String encrypt(String cleartext) {

        //------------------------------------------AES encryption---- ---------------------------------

        //Encryption method: AES128(CBC/PKCS5Padding) + Base64, private key: aabbccddeeffgghh
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            //Two parameters, the first is the private key byte array, the second is the encryption method AES or DES
            SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");
            //Instantiate the encryption class,the parameter is the encryption method, to write the full
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //PKCS5Padding is more efficient than PKCS7Padding, PKCS7Padding can support IOS encryption and decryption
            //Initialization, this method can be added in three ways, according to the
            //encryption algorithm requirements. (1) No third parameter(2) The third parameter is
            SecureRandom random = new SecureRandom();
            //in the random object, random number. (AES cannot use this method)(3) Adopt IVParameterSpec in this code
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

            //------------------------------------------base64 encoding---- ---------------------------------

            //Encryption operation, return the encrypted byte array, and then need to encode.The main
            //codecs are Base64, HEX, UUE, 7 bit, and so on.See what encoding the server needs here.
            //byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));
            //return new BASE64Encoder().encode(encryptedData);

            //Up and down is equivalent, just import the package is different
            //Encrypted byte array
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));
            //Base64 encoding the encrypted byte array
            byte[] base64Data = Base64.encodeBase64(encryptedData);
            //Convert the base64 encoded byte array to a string and return
            return new String(base64Data);

            //------------------------------------------/base64 encoding --- ----------------------------------

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        //------------------------------------------/AES encryption --- ----------------------------------
    }

    /**
     * Decrypt
     *
     * @param encrypted The string before decryption (that is, the encrypted string)
     * @return The decrypted string (that is, the string before encryption)
     */
    public static String decrypt(String encrypted) {

        //---------------------------------------AES decryption ------- ---------------------------------

        try {

            //---------------------------------------base64 decoding ------- --------------------------------

            //byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted);
            //Up and down is equivalent, just import the package is different
            //Convert the string to a base64 encoded byte array
            byte[] encryptedBase64Bytes = encrypted.getBytes();
            //Convert the base64 encoded byte array into a byte array after encryption
            byte[] byteMi = Base64.decodeBase64(encryptedBase64Bytes);

            //---------------------------------------/base64 decoding ------ ---------------------------------

            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(ASE_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //Different from encryption MODE:Cipher.DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, bm);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        //---------------------------------------/AES decryption ------ ----------------------------------
    }

    /**
     * Test
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


//        String content = "98.5674";
        String content = "welmahask@gmail.com";
        // encryption
        System.out.println("pre-encryption:" + content);
        String encryptResult = encrypt(content);

        System.out.println("Encrypted:" + new String(encryptResult));
        // decrypt
        String decryptResult = decrypt(encryptResult);
        System.out.println("Decrypted:" + new String(decryptResult));


    }
}
