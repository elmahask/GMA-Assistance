package com.gma.assistance.gma;

import com.gma.assistance.gma.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Test {
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'E', 'S', 'T', 'T', 'T', 'E', 'S', 'T', 'T', 'T', 'E', 'S', 'T', 'T'};
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static String encrypt(String algorithm, String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec("secret".toCharArray());
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return cipherText.toString();//Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec("secret".toCharArray());
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] plainText = cipher.doFinal(cipherText.getBytes());
        return new String(plainText);
    }

    public static void main(String[] args) {
//        String plainText = "welmahaskqqqqqqqq2423423432424qqqqqqqqqqqq@gmail.com";
//        String password = "GMA-Assistance";
//        String salt = "12345678";
        try {
//            String enc = encrypt("AES", "welmahask@gmail.com");
//            System.out.println(enc);
//            String dec = decrypt("AES", enc);
//            System.out.println(dec);
            String plainText = "wael.mohamed.ahmed2.mohamed.ahmed@gmail.com";
            String password = "GMA-Assistance";
            String salt = "12345678";
            IvParameterSpec ivParameterSpec = AESUtil.generateIv();

            SecretKey key = AESUtil.getKeyFromPassword(password,salt);
//            SecretKey key = AESUtil.getKeyFromPassword(password);

//            String cipherText = AESUtil.encryptPasswordBased(plainText, key, AESUtil.ivParameterSpec);
//            System.out.println(cipherText);
//
//            String decryptedCipherText = AESUtil.decryptPasswordBased(cipherText, key, AESUtil.ivParameterSpec);
//            System.out.println(decryptedCipherText);

            /*------------------------------------------------------*/
            String cipherText1 = encryptPasswordBased(plainText, key);
            System.out.println(cipherText1);

            String decryptedCipherText1 = decryptPasswordBased(cipherText1, key);
            System.out.println(decryptedCipherText1);




        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

//        String encrypt = encrypt(plainText);
//        System.out.println(encrypt.toString());
//        System.out.println("add [B@"+encrypt.toString());
//        String d = decrypt(encrypt);
//        System.out.println(d);
//
//        String encrypt1 = encrypt("wqrshhrhehrdffdhhehhtrvtjrtjreytcyrcjtshhmkvnjxvhyufeiufekjcmnfsiudfwqrshhrhehrdffdhhehhtrvtjrtjreytcyrcjtshhmkvnjxvhyufeiufekjcmnfsiudfnvdnbddiuerehoiroiwlhsgbjgoewqrshhrhehrdffdhhehhtrvtjrtjreytcyrcjtshhmkvnjxvhyufeiufekjcmnfsiudfnvdnbddiuerehoiroiwlhsgbjgoenvdnbddiuerehoiroiwlhsgbjgoe");
//        System.out.println(encrypt1.toString());
//        String d1 = decrypt(encrypt1);
//        System.out.println(d1);

//        System.out.println(UUID.randomUUID().toString());
//        UUID uuid = UUID.randomUUID();
//        String uuidAsString = uuid.toString();
//        System.out.println(uuidAsString);
//        UUID sameUuid = UUID.fromString(uuidAsString);
//        System.out.println(sameUuid.toString());

//        System.out.println(UUID.fromString("welmahask@gmail.com").toString());
//        System.out.println(solution("Wed", 2));
//        System.out.println(solution("Sat", 23));
    }
    static IvParameterSpec ivParameterSpec = AESUtil.generateIv();

    public static String encryptPasswordBased(String plainText, SecretKey key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    public static String decryptPasswordBased(String cipherText, SecretKey key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
    }




    static String password = "GMA-Assistance";
    static String salt = "12345678";
    public static String solution(String S, int K) {
        List<String> list = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        System.out.println(list.indexOf(S));
        System.out.println(list.indexOf(S) + K % 7);
        System.out.println((list.indexOf(S) + K % 7) % 7);
        return list.get((list.indexOf(S) + K % 7) % 7);
    }

    /**
     * Encrypt a string using AES encryption algorithm.
     *
     * @param pwd the password to be encrypted
     * @return the encrypted string
     */
    public static String encrypt(String pwd) {
        String encodedPwd = "";
        try {
            Key key = AESUtil.getKeyFromPassword(password,salt);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            String BasicBase64format = Base64.getEncoder().encodeToString(pwd.getBytes());
            byte[] encVal = c.doFinal(BasicBase64format.getBytes());
            encodedPwd = encVal.toString();
            System.out.println(encodedPwd.toString());
            encodedPwd = Base64.getEncoder().encodeToString(encVal);
            System.out.println(encodedPwd);
//            System.out.println(encVal.toString().split("@")[1]);
//            return encVal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedPwd;//encodedPwd;

    }

    /**
     * Decrypt a string with AES encryption algorithm.
     *
     * @param encryptedData the data to be decrypted
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) {
        String decodedPWD = "";
        try {
            Key key = AESUtil.getKeyFromPassword(password,salt);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
//            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            System.out.println("encryptedData --> "+encryptedData.toString());
            byte[] decordedValue = encryptedData.getBytes();
            System.out.println("decordedValue --> "+decordedValue.toString());
//            decodedPWD = new String(Base64.getDecoder().decode(encryptedData));
            byte[] decValue = c.doFinal(decordedValue);
//            decodedPWD = new String(decValue);
            decodedPWD = new String(Base64.getDecoder().decode(decValue));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedPWD;
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}
