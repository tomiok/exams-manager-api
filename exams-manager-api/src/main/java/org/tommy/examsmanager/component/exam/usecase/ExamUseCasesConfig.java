package org.tommy.examsmanager.component.exam.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.examsmanager.component.exam.domain.ExamRepository;

@Configuration
public class ExamUseCasesConfig {

  @Bean
  public SaveExamService saveExamService(final ExamRepository examRepository) {
    return new SaveExamServiceImpl(examRepository);
  }
}
