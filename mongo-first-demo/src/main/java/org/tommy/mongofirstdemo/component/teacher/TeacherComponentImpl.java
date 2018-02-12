package org.tommy.mongofirstdemo.component.teacher;

import org.apache.commons.lang3.Validate;

public class TeacherComponentImpl implements TeacherComponent {

  private final TeacherEntityGateway entityGateway;

  TeacherComponentImpl(final TeacherEntityGateway entityGateway) {
    Validate.notNull(entityGateway, "entityGateway bean cannot be null");
    this.entityGateway = entityGateway;
  }

  @Override
  public TeacherResponse createTeacher(final TeacherRequest request) {
    return entityGateway.transform(request);
  }

  @Override
  public TeacherResponse findById(final String id) {
    return entityGateway.findById(id);
  }
}
