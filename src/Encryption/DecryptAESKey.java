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

    public static byte[] decryptAesKey(Path mePath, Path keyPath, Path filePath, Path ivPath) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        byte[] encryptedAesKey = Files.readAllBytes(keyPath);

        PrivateKey privateKey = loadPrivateKey("/home/tone/IdeaProjects/practice-enterprise/Encryption-Test/private_pkcs8.pem");

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedData = cipher.doFinal(encryptedAesKey);
        System.out.println(new String(decryptedData));
        return decryptedData;
    }

}
