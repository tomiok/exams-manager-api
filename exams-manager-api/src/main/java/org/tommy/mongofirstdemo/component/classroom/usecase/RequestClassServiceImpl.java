package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.time.Instant;
import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.component.classroom.ClassroomEntityGateway;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;
import org.tommy.mongofirstdemo.component.student.StudentEntityGateway;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.teacher.TeacherEntityGateway;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;

public class RequestClassServiceImpl implements RequestClassService {

  private final StudentEntityGateway studentEntityGateway;

  private final TeacherEntityGateway teacherEntityGateway;

  private final ClassroomEntityGateway classroomEntityGateway;

  public RequestClassServiceImpl(final StudentEntityGateway studentEntityGateway,
                                 final TeacherEntityGateway teacherEntityGateway,
                                 final ClassroomEntityGateway classroomEntityGateway) {
    this.studentEntityGateway = studentEntityGateway;
    this.teacherEntityGateway = teacherEntityGateway;
    this.classroomEntityGateway = classroomEntityGateway;
  }

  @Override
  public ClassRequest requestClass(final Request request) {
    Student s = studentEntityGateway.findById(request.getStudentId());
    Teacher t = teacherEntityGateway.findTeacherById(request.getTeacherId());
    validateComment(request.getComment());
    return classroomEntityGateway
        .save(new ClassRequest(s, t, request.getComment(), ClassStatus.PENDING, Instant.now()));
  }

  private static void validateComment(final String comment) {
    Validate.notBlank(comment, "Comment cannot be blank");
    Validate.isTrue(comment.length() <= 200, "Comment cannot be greater than 200 chars");
  }
}
