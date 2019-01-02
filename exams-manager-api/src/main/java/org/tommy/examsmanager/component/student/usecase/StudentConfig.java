package org.tommy.examsmanager.component.student.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.examsmanager.application.cipher.ApplicationCipherCustomConfig;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;
import org.tommy.examsmanager.component.student.domain.StudentEntityGatewayConfig;

@Configuration
@Import({ ApplicationCipherCustomConfig.class, StudentEntityGatewayConfig.class })
public class StudentConfig {

  @Bean
  public FindStudentService studentComponent(
      final StudentEntityGateway entityGateway
  ) {
    return new FindStudentServiceImpl(entityGateway);
  }

  @Bean
  public SaveStudentService saveStudentService(
      final StudentEntityGateway entityGateway
  ) {
    return new SaveStudentServiceImpl(entityGateway);
  }
}
