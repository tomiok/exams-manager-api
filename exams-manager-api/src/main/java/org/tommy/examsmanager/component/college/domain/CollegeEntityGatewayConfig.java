package org.tommy.examsmanager.component.college.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class CollegeEntityGatewayConfig {

  @Bean
  public CollegeEntityGateway collegeEntityGateway(final MongoTemplate mongoTemplate) {
    return new CollegeEntityGateway(mongoTemplate);
  }
}
