package org.tommy.examsmanager.component.exam.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGatewayConfig;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;
import org.tommy.examsmanager.component.student.domain.StudentEntityGatewayConfig;

@Configuration
@Import({ ExamEntityGatewayConfig.class, StudentEntityGatewayConfig.class })
public class ExamUseCasesConfig {

  @Bean
  public SaveExamService saveExamService(
      final ExamEntityGateway examEntityGateway,
      final StudentEntityGateway studentEntityGateway) {
    return new SaveExamServiceImpl(examEntityGateway, studentEntityGateway);
  }

  @Bean
  public DismissExamService dismissExamService(
      final ExamEntityGateway examEntityGateway
  ) {
    return new DismissExamServiceImpl(examEntityGateway);
  }
}
