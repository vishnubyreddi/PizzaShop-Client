package Encryption;

import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.encoders.Hex;

import java.security.SecureRandom;

public class PasswordEncryption {

    public static String encrypt(String password, String salt) {
        byte[] passwordBytes = password.getBytes();
        byte[] saltBytes = salt.getBytes();
        byte[] hash = SCrypt.generate(passwordBytes, saltBytes, 16384, 8, 1, 32);
        return Hex.toHexString(hash);
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return new String(Hex.toHexString(salt));
    }
}