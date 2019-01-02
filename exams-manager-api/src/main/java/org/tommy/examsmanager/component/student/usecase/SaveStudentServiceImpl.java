package org.tommy.examsmanager.component.student.usecase;

import org.apache.commons.lang3.Validate;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;
import org.tommy.examsmanager.component.student.domain.Student;

public class SaveStudentServiceImpl implements SaveStudentService {

  private final StudentEntityGateway entityGateway;

  public SaveStudentServiceImpl(final StudentEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  @Override
  public String registerStudent(final CreateStudentRequest request) {
    Student student = createStudent(request);
    return entityGateway.saveStudent(student).getId();
  }

  private static Student createStudent(final CreateStudentRequest request) {
    Validate.notNull(request.getEmail(), "email cannot be null");
    return new Student(request.getFirstName(), request.getLastName(), request.getPassword(), request.getEmail());
  }
}
