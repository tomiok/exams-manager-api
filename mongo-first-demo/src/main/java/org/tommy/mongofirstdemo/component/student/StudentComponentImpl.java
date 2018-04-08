package org.tommy.mongofirstdemo.component.student;

import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.component.student.domain.Student;

public class StudentComponentImpl implements StudentComponent {

  private final StudentEntityGateway entityGateway;

  StudentComponentImpl(final StudentEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  @Override
  public String registerStudent(final CreateStudentRequest request) {
    Student student = createStudent(request);
    return entityGateway.saveStudent(student).getId();
  }

  @Override
  public StudentResponse getStudentById(final String id) {
    return entityGateway.findStudentById(id);
  }

  private static Student createStudent(final CreateStudentRequest request) {
    Validate.notNull(request.getEmail(), "email cannot be null");
    return new Student(request.getFirstName(), request.getLastName(), request.getPassword(), request.getEmail());
  }
}
