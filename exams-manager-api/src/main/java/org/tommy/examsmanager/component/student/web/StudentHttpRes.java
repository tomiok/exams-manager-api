package org.tommy.examsmanager.component.student.web;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.student.domain.Student;

@Getter
@Setter
class StudentHttpRes {

  private String firstName;

  private String lastName;

  private String email;

  private List<Exam> exams;

  StudentHttpRes(final Student student) {
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.email = student.getEmail();
    this.exams = student.getExams();
  }
}
