package org.tommy.examsmanager.application.cipher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationCipherTest {

  @Autowired
  private ApplicationCipher applicationCipher;

  @Test
  public void encrypt() {
    String pass = "WhatAa_pass";

    String encrypted = applicationCipher.encrypt(pass);
    assertThat(encrypted).isNotNull();
    assertThat(encrypted).isNotEqualTo(pass);

    String decrypted = applicationCipher.decrypt(encrypted);

    assertThat(decrypted).isEqualTo(pass);
  }
}