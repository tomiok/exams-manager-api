package org.tommy.mongofirstdemo.application.cipher;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCipherCustomConfig {

  @Bean
  public ApplicationCipher applicationCipher() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(128);
      SecretKey secretKey = keyGen.generateKey();
      return new ApplicationCipher(secretKey);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Cannot initialize the keyGenerator", e);
    }
  }
}
