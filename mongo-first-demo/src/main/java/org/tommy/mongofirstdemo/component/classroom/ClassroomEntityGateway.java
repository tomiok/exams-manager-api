package org.tommy.mongofirstdemo.component.classroom;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Set;
import org.bson.types.ObjectId;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.DuplicatedClassException;

public class ClassroomEntityGateway {

  private ClassRepository classRepository;

  private static final long DAYS = 10;

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

    if (!isTooOld(classSaved)) {
      throw new DuplicatedClassException();
    }
  }

  private boolean isTooOld(final ClassRequest classRequest) {
    return classRequest.getDateCreated().isBefore(getFromDaysBefore());
  }

  private Instant getFromDaysBefore() {
    LocalDate localDate = LocalDate.now();
    return localDate.minusDays(DAYS).atStartOfDay().toInstant(ZoneOffset.UTC);
  }
}
