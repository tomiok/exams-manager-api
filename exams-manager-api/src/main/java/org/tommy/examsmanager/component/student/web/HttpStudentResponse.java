package org.tommy.examsmanager.component.student.web;

import lombok.Getter;
import lombok.Setter;
import org.tommy.examsmanager.component.student.domain.Student;

@Getter
@Setter
final class HttpStudentResponse {

  String id;
  String firstName;
  String lastName;
  String email;

  HttpStudentResponse(Student student) {
    this.id = student.getId();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.email = student.getEmail();
  }
}
