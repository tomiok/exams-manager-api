package org.tommy.examsmanager.component.student.usecase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommy.examsmanager.component.student.domain.Student;

public interface FindStudentService {

  StudentResponse getStudentById(final String id);

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class StudentResponse {

    private String email;

    private String firstName;

    private String lastName;

    public static StudentResponse fromModel(final Student student) {
      return new StudentResponse(student.getEmail(), student.getFirstName(), student.getLastName());
    }
  }
}

