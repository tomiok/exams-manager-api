package org.tommy.mongofirstdemo.component.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.domain.teacher.TeacherRepository;

@Configuration
public class TeacherConfig {

  @Autowired
  private TeacherRepository teacherRepository;

  @Bean
  public TeacherEntityGateway teacherEntityGateway() {
    return new TeacherEntityGateway(teacherRepository);
  }

  @Bean
  public TeacherComponent teacherComponent(final TeacherEntityGateway teacherEntityGateway) {
    return new TeacherComponentImpl(teacherEntityGateway);
  }
}
