package org.tommy.mongofirstdemo.component.classroom;

import java.time.Instant;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;
import org.tommy.mongofirstdemo.component.student.StudentMother;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.teacher.TeacherMother;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;

public final class ClassMother {

  public static ClassRequest create(final Student student, final Teacher teacher, final String comment) {
    return new ClassRequest(student, teacher, comment, ClassStatus.PENDING, Instant.now());
  }

  public static ClassRequest createOld(final Student student, final Teacher teacher, final String comment) {
    return new ClassRequest(student, teacher, comment, ClassStatus.PENDING, Instant.MIN);
  }

  public static ClassRequest createStandardClass() {
    Student s = StudentMother.createCapote();
    s.setId("507f1f77bcf86cd799439011");
    Teacher t = TeacherMother.createAlbertEinstein();
    t.setId("507f1f77bcf86cd799439012");
    String comment = "Please teach me maths!";

    return ClassMother.create(s, t, comment);
  }
}
