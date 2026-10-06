import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption {

    public static String encrypt(String originalText) throws Exception {

        // Generate AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        SecretKey aesKey = keyGenerator.generateKey();

        // Convert text to bytes
        byte[] originalData =
                originalText.getBytes(StandardCharsets.UTF_8);

        // Generate random IV
        byte[] initializationVector = new byte[12];

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);

        // Create AES-GCM cipher
        Cipher aesCipher =
                Cipher.getInstance("AES/GCM/NoPadding");

        GCMParameterSpec gcmSettings =
                new GCMParameterSpec(128, initializationVector);

        // Prepare encryption
        aesCipher.init(
                Cipher.ENCRYPT_MODE,
                aesKey,
                gcmSettings
        );

        // Encrypt
        byte[] encryptedData =
                aesCipher.doFinal(originalData);

        // Convert encrypted data to Base64
        return Base64.getEncoder()
                .encodeToString(encryptedData);
    }
}