import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESencrypt {

    public static void main(String[] args) throws Exception {

        
        KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");

        
        aesKeyGenerator.init(256);

        
        SecretKey aesKey = aesKeyGenerator.generateKey();

        
        String originalText = "ABHIRAM";

        
        byte[] originalData = originalText.getBytes();

       
        byte[] initializationVector = new byte[12];

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);

       
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");

       
        GCMParameterSpec gcmSettings =
                new GCMParameterSpec(128, initializationVector);

        // 9. Prepare AES for encryption
        aesCipher.init(
                Cipher.ENCRYPT_MODE,
                aesKey,
                gcmSettings
        );

       
        byte[] encryptedData = aesCipher.doFinal(originalData);

      
        String encryptedText =
                Base64.getEncoder().encodeToString(encryptedData);    
        System.out.println("Original text: " + originalText);
        System.out.println("Encryption key (Base64): " +
        Base64.getEncoder().encodeToString(aesKey.getEncoded()));
        System.out.println("Encrypted text: " + encryptedText);

        System.out.println("IV (Base64): " +
        Base64.getEncoder().encodeToString(initializationVector));
    }
}
