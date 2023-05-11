package Encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.util.Base64;


public class DecryptAESKey {

    private static PrivateKey loadPrivateKey(String path) throws Exception {
        try (FileReader fileReader = new FileReader(path);
             PemReader pemReader = new PemReader(fileReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] pemContent = pemObject.getContent();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(pemContent);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePrivate(privateKeySpec);
        }
    }

    public static String decryptAesKey(Path mePath, Path keyPath, Path filePath, Path ivPath) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        byte[] encryptedAesKey = Files.readAllBytes(keyPath);

        PrivateKey privateKey = loadPrivateKey("/home/tone/IdeaProjects/practice-enterprise/Encryption-Test/private_pkcs8.pem");

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedData = cipher.doFinal(encryptedAesKey);
        String strDecryptedData = new String(decryptedData);
        System.out.println(strDecryptedData);
        return strDecryptedData;
    }

    // ENCRYPT

    public static void encryptAesKey(String publicPath) throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        SecretKey secretKey = generateAESKey();
        byte[] encryptedAesKey = encryptAESKey(secretKey, publicPath);

        System.out.println(Base64.getEncoder().encodeToString(encryptedAesKey));
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256); // The AES key size in number of bits
        return generator.generateKey();
    }

    private static byte[] encryptAESKey(SecretKey aesKey, String publicKeyPath) throws Exception {
        byte[] publicKeyBytes = Files.readAllBytes(Path.of(publicKeyPath));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(spec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, pk);

        return cipher.doFinal(aesKey.getEncoded());
    }

}
