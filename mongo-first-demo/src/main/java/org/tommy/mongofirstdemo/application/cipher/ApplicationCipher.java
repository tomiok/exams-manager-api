package org.tommy.mongofirstdemo.application.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ApplicationCipher {

  private Cipher cipher;

  private Cipher decryptCipher;

  public ApplicationCipher(final SecretKey key) {
    try {
      this.cipher = Cipher.getInstance("AES");
      this.decryptCipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      decryptCipher.init(Cipher.DECRYPT_MODE, key);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
      e.printStackTrace();
    }
  }

  public String encrypt(final String s) {
    try {
      byte[] utf8Bytes = s.getBytes("UTF8");

      byte[] encrypted = cipher.doFinal(utf8Bytes);

      return Base64.getEncoder().encodeToString(encrypted);
    } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
      throw new IllegalStateException("Cannot encrypt the String");
    }
  }

  public String decrypt(final String s) {
    try {
      byte[] decoded = Base64.getDecoder().decode(s);
      byte[] strBytes = decryptCipher.doFinal(decoded);
      return new String(strBytes, "UTF8");
    } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
      throw new IllegalStateException("Cannot decrypt the string");
    }
  }
}
