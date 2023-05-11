package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
public class DecryptCsvFile {
    public static void decryptCsv(Path ivPath, Path encFile) throws Exception
    {
        // Convert Hex to byte array
        byte[] keyBytes = hexStringToByteArray("609edc6f66c3b03ac84632726f98790c842c7b445fc86c2bcbe54be191484b89");
        byte[] ivBytes = hexStringToByteArray("96fd1455632290437c9d37ee68d6d5fb");


        // Create key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedData = Files.readAllBytes(encFile); // Read encrypted data
        byte[] decryptedData = cipher.doFinal(encryptedData); // Decrypt data

        Files.write(Paths.get("data.csv"), decryptedData); // Write decrypted data to file
    }

    public static void encryptCsv(Path decFile, Path encFile) throws Exception
    {
        String[] keyIv = generateKeyAndIv();

        // Convert Hex to byte array
        byte[] keyBytes = hexStringToByteArray(keyIv[0]);
        byte[] ivBytes = hexStringToByteArray(keyIv[1]);

        // Create key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decryptedData = Files.readAllBytes(decFile); // Read decrypted data
        byte[] encryptedData = cipher.doFinal(decryptedData); // Encrypt data

        Files.write(encFile, encryptedData); // Write encrypted data to file
    }

    // TODO: safe keys in file

    private static String[] generateKeyAndIv() throws Exception {
        SecureRandom secureRandom = new SecureRandom();

        // Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, secureRandom); // 256 bits = 32 bytes
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        String keyHex = bytesToHexString(keyBytes);

        // IV
        byte[] ivBytes = new byte[16]; // 16 bytes = 128 bits
        secureRandom.nextBytes(ivBytes);
        String ivHex = bytesToHexString(ivBytes);

        return new String[]{keyHex, ivHex};
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    private static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
