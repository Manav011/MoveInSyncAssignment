package floormanagement.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.security.Key;

/**
 * The Encryptdecrypt class provides methods for encrypting and decrypting
 * strings using the AES encryption algorithm.
 */
public class Encryptdecrypt {
    private byte[] key_value;

    /**
     * Constructs an Encryptdecrypt object with the specified key.
     * 
     * @param key The encryption key. Must be 16 characters long.
     */
    public Encryptdecrypt(String key) {
        this.key_value = key.getBytes();
    }

    /**
     * Generates a secret key based on the provided encryption key.
     * 
     * @return The generated secret key.
     */
    private Key genKey() {
        Key key = new SecretKeySpec(this.key_value, "AES");
        return key;
    }

    /**
     * Encrypts the given string using AES encryption.
     * 
     * @param s The string to encrypt.
     * @return The encrypted string.
     * @throws Exception if an error occurs during encryption.
     */
    public static String encrypt(String s) throws Exception {
        Key key = genKey();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted_value = c.doFinal(s.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encrypted_value);
    }

    /**
     * Authenticating the user with encryption
     * 
     * @param enteredPassword password entered by the user
     * @return True if the password matches with the hashed password
     */
    public static boolean authenticate(String enteredPassword, User user) {
        return encrypt(enteredPassword).equals(user.hashedPassword);
    }
}
