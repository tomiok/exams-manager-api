package org.tommy.mongofirstdemo.component.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.usecase.FindClassService;
import org.tommy.mongofirstdemo.component.classroom.usecase.FindClassServiceImpl;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassService;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassServiceImpl;
import org.tommy.mongofirstdemo.component.student.StudentEntityGateway;
import org.tommy.mongofirstdemo.component.teacher.TeacherEntityGateway;

@Configuration
public class ClassroomConfig {

  @Autowired
  private TeacherEntityGateway teacherEntityGateway;

  @Autowired
  private StudentEntityGateway studentEntityGateway;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private ClassroomEntityGateway classroomEntityGateway;

  @Bean
  public RequestClassService requestClassService() {
    return new RequestClassServiceImpl(studentEntityGateway, teacherEntityGateway, classroomEntityGateway);
  }

  @Bean
  public FindClassService findClassService() {
    return new FindClassServiceImpl(classroomEntityGateway);
  }

  @Bean
  public ClassroomEntityGateway classroomEntityGateway() {
    return new ClassroomEntityGateway(classRepository);
  }
}
