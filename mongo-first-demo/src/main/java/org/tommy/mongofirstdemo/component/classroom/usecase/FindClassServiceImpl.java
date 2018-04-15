package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.util.Set;
import org.tommy.mongofirstdemo.component.classroom.ClassroomEntityGateway;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.shared.UserType;

public class FindClassServiceImpl implements FindClassService {

  private final ClassroomEntityGateway classroomEntityGateway;

  public FindClassServiceImpl(final ClassroomEntityGateway classroomEntityGateway) {
    this.classroomEntityGateway = classroomEntityGateway;
  }

  @Override
  public Set<ClassRequest> findClass(final String id, final UserType userType) {
    return UserType.TEACHER.equals(userType) ?
           classroomEntityGateway.findByTeacherId(id) : classroomEntityGateway.findByStudentId(id);
  }
}