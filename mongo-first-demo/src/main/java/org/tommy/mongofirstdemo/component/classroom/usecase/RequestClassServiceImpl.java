package org.tommy.mongofirstdemo.component.classroom.usecase;

import org.apache.tomcat.util.codec.EncoderException;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.student.domain.StudentRepository;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

public class RequestClassServiceImpl implements RequestClassService {

  private final StudentRepository studentRepository;

  private final TeacherRepository teacherRepository;

  private final ClassRepository classRepository;

  public RequestClassServiceImpl(final StudentRepository studentRepository,
                                 final TeacherRepository teacherRepository,
                                 final ClassRepository classRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
    this.classRepository = classRepository;
  }

  @Override
  public void requestClass(final Request request) {
    Student s = studentRepository.findById(request.getStudentId()).orElseThrow(IllegalArgumentException::new);

  }
}
