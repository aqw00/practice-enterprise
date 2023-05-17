package Encryption;

import Reading.ReadTxtFile;
import io.netty.util.internal.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
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
import java.util.List;

public class DecryptCsvFile {
    public static void decryptCsv(String ivPathStr, String encFileStr, byte[] keyBytes, String decryptedCsvStr) throws Exception
    {
        Path ivPath = Path.of(ivPathStr);
        Path encFile = Path.of(encFileStr);
        Path decryptedCsv = Path.of(decryptedCsvStr);

        // 1 String keyStr = aes_keyStr.substring(0, Math.min(aes_keyStr.length(), 64));
        // Convert Hex to byte array
        // 2 byte[] keyBytes = hexStringToByteArray(aes_keyStr);
        //byte[] ivBytes = Files.readAllBytes(ivPath);
        List<String> ivRawList = ReadTxtFile.txtFileHandeling(ivPathStr, true, "");
        String ivRawStr = ivRawList.get(0);
        String ivStr = ivRawStr.substring(0, Math.min(ivRawStr.length(), 64));
        byte[] ivBytes = hexStringToByteArray(ivStr);

        // Create key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedData = Files.readAllBytes(encFile); // Read encrypted data
        byte[] decryptedData = cipher.doFinal(encryptedData); // Decrypt data

        Files.write(decryptedCsv, decryptedData); // Write decrypted data to file
    }

    public static String decryptString(String data)
    {
        try {
            byte[] keyBytes = hexStringToByteArray("e1a5bbffb264be79d47e1b6828e93f3f896f23acf4af4db68745379024db0a90");
            byte[] ivBytes = hexStringToByteArray("22abea5d5d3ae5e796562f30e53c7199");

            // Create key and IV
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            // Decrypt
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedData = data.getBytes(StandardCharsets.UTF_8); // Convert string to byte array
            byte[] decryptedData = cipher.doFinal(encryptedData);

            String decryptedString = Base64.getEncoder().encodeToString(decryptedData); // Convert encrypted data to string

            return decryptedString;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return "";
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

    // ENCRYPT

    public static String[] encryptCsv(Path decFile, Path encFile) throws Exception
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

        System.out.println("aeskey: " + keyIv[0]);
        System.out.println("ivkey: " + keyIv[1]);

        Files.write(encFile, encryptedData); // Write encrypted data to file

        ReadTxtFile.newTxtFile(keyIv[1], "C:\\Users\\aqw00\\IdeaProjects\\practice-enterprise\\sharedFolder\\aes_iv.txt"); //write iv to file

        return keyIv;

    }

    // this is for encrypting password for sftp
    public static String encryptPass(String strToEncrypt) throws Exception
    {
        byte[] keyBytes = hexStringToByteArray("e1a5bbffb264be79d47e1b6828e93f3f896f23acf4af4db68745379024db0a90");
        byte[] ivBytes = hexStringToByteArray("22abea5d5d3ae5e796562f30e53c7199");

        // Create key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decryptedData = strToEncrypt.getBytes(StandardCharsets.UTF_8); // Convert string to byte array

        byte[] encryptedData = cipher.doFinal(decryptedData); // Encrypt data

        String encryptedString = Base64.getEncoder().encodeToString(encryptedData); // Convert encrypted data to string

        // System.out.println("Encrypted string: " + encryptedString);
        return encryptedString;
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

}
