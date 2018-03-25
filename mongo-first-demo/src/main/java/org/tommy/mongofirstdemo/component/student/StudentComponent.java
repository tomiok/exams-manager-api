package org.tommy.mongofirstdemo.component.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommy.mongofirstdemo.component.student.domain.Student;

public interface StudentComponent {

  String registerStudent(final CreateStudentRequest request);

  StudentResponse getStudentById(final String id);

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class CreateStudentRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class StudentResponse {

    private String email;

    private String firstName;

    private String lastName;

    static StudentResponse fromModel(final Student student) {
      return new StudentResponse(student.getEmail(), student.getFirstName(), student.getLastName());
    }
  }
}


