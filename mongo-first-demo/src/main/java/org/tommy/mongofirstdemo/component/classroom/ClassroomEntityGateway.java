package org.tommy.mongofirstdemo.component.classroom;

import java.util.Set;
import org.bson.types.ObjectId;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;

public class ClassroomEntityGateway {

  private ClassRepository classRepository;

  public ClassroomEntityGateway(final ClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  public Set<ClassRequest> findByTeacherId(final String teacherId) {
    return classRepository.findByTeacherId(new ObjectId(teacherId));
  }

  public Set<ClassRequest> findByStudentId(final String studentId) {
    return classRepository.findByStudentId(new ObjectId(studentId));
  }

  public ClassRequest save(final ClassRequest c) {
    verifyClassRequest(c.getStudent().getId(), c.getTeacher().getId());
    return classRepository.insert(c);
  }

  private void verifyClassRequest(final String studentId, final String teacherId) {
    ClassRequest classSaved = classRepository
        .findByStudentIdAndTeacherId(new ObjectId(studentId), new ObjectId(teacherId));

  }
}
