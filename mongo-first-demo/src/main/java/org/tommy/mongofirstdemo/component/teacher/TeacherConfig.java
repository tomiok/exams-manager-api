package org.tommy.mongofirstdemo.component.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.component.teacher.usecase.Update;
import org.tommy.mongofirstdemo.component.teacher.usecase.UpdateTeacherImpl;
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
  public Update update() {
    return new UpdateTeacherImpl(teacherEntityGateway());
  }

  @Bean
  public TeacherComponent teacherComponent() {
    return new TeacherComponentImpl(teacherEntityGateway(), update());
  }
}
