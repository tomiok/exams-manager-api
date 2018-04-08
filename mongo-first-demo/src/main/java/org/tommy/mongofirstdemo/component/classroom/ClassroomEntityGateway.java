package org.tommy.mongofirstdemo.component.classroom;

import java.util.Set;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;

public class ClassroomEntityGateway {

  private ClassRepository classRepository;

  public ClassroomEntityGateway(final ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  public Set<ClassRequest> findByTeacherId(final String teacherId) {
    return classRepository.findByTeacherId(teacherId);
  }

  public Set<ClassRequest> findByStudentId(final String studentId) {
    return classRepository.findByStudentId(studentId);
  }

  public ClassRequest save(final ClassRequest c) {
    return classRepository.save(c);
  }
}
