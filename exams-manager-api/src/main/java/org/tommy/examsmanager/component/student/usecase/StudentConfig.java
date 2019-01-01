package org.tommy.examsmanager.component.student.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;
import org.tommy.examsmanager.application.cipher.ApplicationCipherCustomConfig;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;
import org.tommy.examsmanager.component.student.domain.StudentRepository;

@Configuration
@Import({ ApplicationCipherCustomConfig.class })
public class StudentConfig {

  @Bean
  public StudentEntityGateway studentEntityGateway(
      final ApplicationCipher applicationCipher,
      final StudentRepository studentRepository) {
    return new StudentEntityGateway(studentRepository, applicationCipher);
  }

  @Bean
  public FindStudentService studentComponent(
      final ApplicationCipher applicationCipher,
      final StudentRepository studentRepository
  ) {
    return new FindStudentServiceImpl(studentEntityGateway(applicationCipher, studentRepository));
  }

  @Bean
  public SaveStudentService saveStudentService(
      final ApplicationCipher applicationCipher,
      final StudentRepository studentRepository
  ) {
    return new SaveStudentServiceImpl(studentEntityGateway(applicationCipher, studentRepository));
  }
}
