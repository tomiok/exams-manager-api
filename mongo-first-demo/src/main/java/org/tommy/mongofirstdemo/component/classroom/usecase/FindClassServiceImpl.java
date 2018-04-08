package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.util.Set;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;

public class FindClassServiceImpl implements FindClassService {

  private final ClassRepository classRepository;

  public FindClassServiceImpl(final ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  @Override
  public Set<ClassRequest> findClassRequestsByTeacherId(final String teacherId) {
    return classRepository.findByTeacherId(teacherId);
  }

  @Override
  public Set<ClassRequest> findClassRequestsByStudentId(final String studentId) {
    return classRepository.findByStudentId(studentId);
  }
}