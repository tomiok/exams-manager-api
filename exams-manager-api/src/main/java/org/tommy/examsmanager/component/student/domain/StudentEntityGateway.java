package org.tommy.examsmanager.component.student.domain;

import static org.tommy.examsmanager.component.student.usecase.FindStudentService.StudentResponse.fromModel;

import java.util.Optional;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentRepository;
import org.tommy.examsmanager.component.student.usecase.FindStudentService;

public class StudentEntityGateway {

  private final StudentRepository studentRepository;

  private final ApplicationCipher applicationCipher;

  public StudentEntityGateway(final StudentRepository studentRepository,
                              final ApplicationCipher applicationCipher) {
    this.studentRepository = studentRepository;
    this.applicationCipher = applicationCipher;
  }

  public Student saveStudent(final Student student) {
    Optional<String> pass = Optional.ofNullable(student.getPassword());
    pass.ifPresent(s -> student.setPassword(applicationCipher.encrypt(s)));
    return studentRepository.save(student);
  }

  public Student findStudentById(final String id) {
    return studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  public Student findById(final String id) {
    return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
