package Encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemReader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class DecryptAESKey
{
    private static PrivateKey loadPrivateKey(String path) throws Exception
    {
        try (FileReader fileReader = new FileReader(path);
             PEMParser pemParser = new PEMParser(fileReader))
        {
            Object object = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            KeyPair kp;

            if (object instanceof PEMKeyPair)
            {
                kp = converter.getKeyPair((PEMKeyPair) object);
            }
            else
            {
                throw new RuntimeException("Unsupported key format! Only PKCS#1 supported.");
            }

            return kp.getPrivate();
        }
    }

    public static byte[] decryptAesKey(String mePathStr, String keyPathStr) throws Exception
    {
        Path keyPath = Path.of(keyPathStr);

        Security.addProvider(new BouncyCastleProvider());

        byte[] encryptedAesKey = Files.readAllBytes(keyPath);

        PrivateKey privateKey = loadPrivateKey(mePathStr);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encryptedAesKey);
    }

    // ENCRYPT
    public static void encryptAesKey(String publicPath, String[] aes_key, Path aes_keyEnc) throws Exception
    {
        // Convert Hex to byte array
        byte[] keyBytes = hexStringToByteArray(aes_key[0]);

        // Create key
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        Security.addProvider(new BouncyCastleProvider());

        byte[] encryptedAesKey = encryptAESKey(secretKey, publicPath);

        System.out.println(Base64.getEncoder().encodeToString(encryptedAesKey));
        Files.write(aes_keyEnc, encryptedAesKey); // write to file
    }

    private static byte[] encryptAESKey(SecretKey aesKey, String publicKeyPath) throws Exception
    {
        PublicKey pk = getPublicKey(publicKeyPath);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, pk);

        return cipher.doFinal(aesKey.getEncoded());
    }

    private static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static PublicKey getPublicKey(String filename) throws Exception
    {
        File file = new File(filename);
        try (FileReader keyReader = new FileReader(file); PemReader pemReader = new PemReader(keyReader))
        {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pemReader.readPemObject().getContent());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        }
    }
}