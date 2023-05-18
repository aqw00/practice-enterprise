package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class VerifySignature {
    public static void verifySig (String publicPathStr, String sigPathStr, String keyPathStr)
    {
        try
        {
            Path publicPath = Path.of(publicPathStr);
            Path sigPath = Path.of(sigPathStr);
            Path keyPath = Path.of(keyPathStr);

            // Load public key
            byte[] keyBytes = Files.readAllBytes(publicPath);
            String publicKeyContent = new String(keyBytes);
            publicKeyContent = publicKeyContent
                    .replaceAll("\\n", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");
            keyBytes = Base64.getDecoder().decode(publicKeyContent);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(spec);

            // Load the signature
            byte[] signatureBytes = Files.readAllBytes(sigPath);

            // Load the data
            byte[] dataBytes = Files.readAllBytes(keyPath);

            // Initialize a Signature object for verification and verify the data
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(dataBytes);

            boolean isCorrect = publicSignature.verify(signatureBytes);
            System.out.println("Signature correct: " + isCorrect);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    // Make sig

    public static void createSig(String meKey, Path encAasKey, String sigPath) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        PrivateKey privateKey = readPrivateKey(meKey);

        // Load the data to be signed
        byte[] data = Files.readAllBytes(encAasKey);

        // Create a Signature object and initialize it with the private key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        // Sign the data
        signature.update(data);
        byte[] signedData = signature.sign();

        // Save the signed data
        try (FileOutputStream fos = new FileOutputStream(sigPath)) {
            fos.write(signedData);
        }
    }

    private static PrivateKey readPrivateKey(String meKey) throws IOException {
        try (Reader reader = new FileReader(meKey)) {
            PEMParser pemParser = new PEMParser(reader);
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            Object object = pemParser.readObject();
            KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
            return kp.getPrivate();
        }
    }

}
