package org.tommy.mongofirstdemo.component.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.application.cipher.ApplicationCipher;
import org.tommy.mongofirstdemo.component.teacher.usecase.update.Update;
import org.tommy.mongofirstdemo.component.teacher.usecase.update.UpdateTeacherImpl;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

@Configuration
public class TeacherConfig {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private ApplicationCipher applicationCipher;

  @Bean
  public TeacherEntityGateway teacherEntityGateway() {
    return new TeacherEntityGateway(teacherRepository, applicationCipher);
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
