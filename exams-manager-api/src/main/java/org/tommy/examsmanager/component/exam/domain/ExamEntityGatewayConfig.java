package org.tommy.examsmanager.component.exam.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ExamEntityGatewayConfig {

  @Bean
  public ExamEntityGateway examEntityGateway(
      final ExamRepository examRepository,
      final MongoTemplate mongoTemplate
  ) {
    return new ExamEntityGateway(examRepository, mongoTemplate);
  }
}
