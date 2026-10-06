import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Randomkey {

    public static void main(String[] args) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        keyGenerator.init(256);

        SecretKey key = keyGenerator.generateKey();
        System.out.println("Key size: " + key.getEncoded().length + " bytes");

        System.out.println(key);
    }
}