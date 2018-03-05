package org.tommy.mongofirstdemo.component.teacher.usecase.find;

import java.util.Set;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;
import org.tommy.mongofirstdemo.domain.teacher.Level;

public interface FindTeacher {

  Set<TeacherComponent.TeacherResponse> findBySignatures(final SignatureCommand command);

  class SignatureCommand {

    private String signature;

    private Level level;
  }
}
