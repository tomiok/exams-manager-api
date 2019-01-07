package org.tommy.examsmanager.component.student.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;

@Configuration
public class StudentEntityGatewayConfig {

  @Bean
  public StudentEntityGateway studentEntityGateway(
      final ApplicationCipher applicationCipher,
      final StudentRepository studentRepository,
      final MongoTemplate mongoTemplate) {
    return new StudentEntityGateway(studentRepository, applicationCipher, mongoTemplate);
  }
}
