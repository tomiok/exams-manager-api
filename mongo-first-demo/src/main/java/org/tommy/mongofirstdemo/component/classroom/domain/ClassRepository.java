package org.tommy.mongofirstdemo.component.classroom.domain;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassRequest, String> {

  Set<ClassRequest> findByStudentId(final String studentId);

  Set<ClassRequest> findByTeacherId(final String teacherId);
}

