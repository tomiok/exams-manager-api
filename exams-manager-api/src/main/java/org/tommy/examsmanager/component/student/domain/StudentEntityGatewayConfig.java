package org.tommy.examsmanager.component.student.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;

@Configuration
public class StudentEntityGatewayConfig {

  @Bean
  public StudentEntityGateway studentEntityGateway(
      final ApplicationCipher applicationCipher,
      final StudentRepository studentRepository) {
    return new StudentEntityGateway(studentRepository, applicationCipher);
  }
}
