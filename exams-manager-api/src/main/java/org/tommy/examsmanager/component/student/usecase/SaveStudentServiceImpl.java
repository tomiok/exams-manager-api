package org.tommy.examsmanager.component.student.usecase;

import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

public class SaveStudentServiceImpl implements SaveStudentService {

  private final StudentEntityGateway entityGateway;

  SaveStudentServiceImpl(final StudentEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  static Student createStudent(final CreateStudentRequest request) {
    return new Student(request.getFirstName(), request.getLastName(), request.getPassword(), request.getEmail());
  }

  @Override
  public Student registerStudent(final CreateStudentRequest request) {
    Student student = createStudent(request);
    return entityGateway.saveStudent(student);
  }
}
