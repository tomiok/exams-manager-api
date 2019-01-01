package org.tommy.examsmanager.component.exam.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGatewayConfig;

@Configuration
@Import({ ExamEntityGatewayConfig.class })
public class ExamUseCasesConfig {

  @Bean
  public SaveExamService saveExamService(final ExamEntityGateway eg) {
    return new SaveExamServiceImpl(eg);
  }
}
