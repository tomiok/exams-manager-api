package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.time.Instant;
import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;
import org.tommy.mongofirstdemo.component.shared.EntityNotFoundException;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.student.domain.StudentRepository;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

public class RequestClassServiceImpl implements RequestClassService {

  private final StudentRepository studentRepository;

  private final TeacherRepository teacherRepository;

  private final ClassRepository classRepository;

  public RequestClassServiceImpl(final StudentRepository studentRepository,
                                 final TeacherRepository teacherRepository,
                                 final ClassRepository classRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
    this.classRepository = classRepository;
  }

  @Override
  public ClassRequest requestClass(final Request request) {
    Student s = studentRepository.findById(request.getStudentId()).orElseThrow(EntityNotFoundException::new);
    Teacher t = teacherRepository.findById(request.getTeacherId()).orElseThrow(EntityNotFoundException::new);
    validateComment(request.getComment());
    return classRepository
        .save(new ClassRequest(s, t, request.getComment(), ClassStatus.PENDING, Instant.now()));
  }

  private static void validateComment(final String comment) {
    Validate.notBlank(comment, "Comment cannot be blank");
    Validate.isTrue(comment.length() <= 200, "Comment cannot be greater than 200 chars");
  }
}
