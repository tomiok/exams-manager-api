package org.tommy.mongofirstdemo.component.student;

import org.tommy.mongofirstdemo.application.cipher.ApplicationCipher;
import org.tommy.mongofirstdemo.domain.student.Student;
import org.tommy.mongofirstdemo.domain.student.StudentRepository;

public class StudentEntityGateway {

  private final StudentRepository studentRepository;

  private final ApplicationCipher applicationCipher;

  public StudentEntityGateway(final StudentRepository studentRepository,
                              final ApplicationCipher applicationCipher) {
    this.studentRepository = studentRepository;
    this.applicationCipher = applicationCipher;
  }

  Student saveStudent(final Student student) {
    String pass = student.getPassword();
    student.setPassword(applicationCipher.encrypt(pass));
    return studentRepository.save(student);
  }
}
