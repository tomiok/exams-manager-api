package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.util.Set;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;

public interface FindClassService {

  Set<ClassRequest> findClassRequestsByTeacherId(final String teacherId);

  Set<ClassRequest> findClassRequestsByStudentId(final String studentId);
}
