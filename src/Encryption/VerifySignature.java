package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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
}
