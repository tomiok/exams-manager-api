package org.tommy.mongofirstdemo.component.teacher.usecase.find;

import java.util.Set;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;
import org.tommy.mongofirstdemo.domain.teacher.TeacherRepository;

public class FindTeacherImpl implements FindTeacher {

  private final TeacherRepository teacherRepository;

  public FindTeacherImpl(final TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  @Override
  public Set<TeacherComponent.TeacherResponse> findBySignatures(final SignatureCommand command) {
    return null;
  }


}
