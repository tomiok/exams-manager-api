package org.tommy.mongofirstdemo.component.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.application.cipher.ApplicationCipher;
import org.tommy.mongofirstdemo.component.student.domain.StudentRepository;

@Configuration
public class StudentConfig {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ApplicationCipher applicationCipher;

  @Bean
  public StudentEntityGateway studentEntityGateway() {
    return new StudentEntityGateway(studentRepository, applicationCipher);
  }
  @Bean
  public StudentComponent studentComponent() {
    return new StudentComponentImpl(studentEntityGateway());
  }
}
