package org.tommy.mongofirstdemo.component.teacher;

public class TeacherComponentImpl implements TeacherComponent {

  private final TeacherEntityGateway entityGateway;

  TeacherComponentImpl(final TeacherEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  @Override
  public TeacherResponse createTeacher(final TeacherRequest request) {
    return entityGateway.transform(request);
  }
}
