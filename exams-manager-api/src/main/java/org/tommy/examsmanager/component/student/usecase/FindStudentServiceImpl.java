package org.tommy.examsmanager.component.student.usecase;

import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

public class FindStudentServiceImpl implements FindStudentService {

  private final StudentEntityGateway entityGateway;

  FindStudentServiceImpl(final StudentEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  @Override
  public StudentResponse getStudentById(final String id) {
    return entityGateway.findStudentById(id);
  }
}
