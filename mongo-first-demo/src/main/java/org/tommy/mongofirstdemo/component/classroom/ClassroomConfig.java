package org.tommy.mongofirstdemo.component.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassService;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassServiceImpl;
import org.tommy.mongofirstdemo.component.student.domain.StudentRepository;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

@Configuration
public class ClassroomConfig {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ClassRepository classRepository;

  @Bean
  public RequestClassService requestClassService() {
    return new RequestClassServiceImpl(studentRepository, teacherRepository, classRepository);
  }
}
