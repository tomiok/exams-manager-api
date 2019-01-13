package org.tommy.examsmanager.component.college.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.examsmanager.component.college.domain.CollegeEntityGateway;
import org.tommy.examsmanager.component.college.domain.CollegeEntityGatewayConfig;

@Configuration
@Import(CollegeEntityGatewayConfig.class)
public class CollegeUseCasesConfigurations {

  @Bean
  public FindCollegeService findCollegeService(final CollegeEntityGateway collegeEntityGateway) {
    return new FindCollegeServiceImpl(collegeEntityGateway);
  }

  @Bean
  public SaveCollegeService saveCollegeService(final CollegeEntityGateway collegeEntityGateway) {
    return new SaveCollegeServiceImpl(collegeEntityGateway);
  }

}
