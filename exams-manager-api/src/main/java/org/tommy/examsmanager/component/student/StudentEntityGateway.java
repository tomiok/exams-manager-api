package org.tommy.examsmanager.component.student;

import static org.tommy.examsmanager.component.student.StudentComponent.StudentResponse.fromModel;

import java.util.Optional;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentRepository;

public class StudentEntityGateway {

  private final StudentRepository studentRepository;

  private final ApplicationCipher applicationCipher;

  public StudentEntityGateway(final StudentRepository studentRepository,
                              final ApplicationCipher applicationCipher) {
    this.studentRepository = studentRepository;
    this.applicationCipher = applicationCipher;
  }

  Student saveStudent(final Student student) {
    Optional<String> pass = Optional.ofNullable(student.getPassword());
    pass.ifPresent(s -> student.setPassword(applicationCipher.encrypt(s)));
    return studentRepository.save(student);
  }

  StudentComponent.StudentResponse findStudentById(final String id) {
    Student student = studentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return fromModel(student);
  }

  public Student findById(final String id) {
    return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }
}
