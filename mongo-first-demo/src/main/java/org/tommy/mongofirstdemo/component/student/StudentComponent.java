package org.tommy.mongofirstdemo.component.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface StudentComponent {

  String registerStudent(final CreateStudentRequest request);

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
}


