package org.tommy.mongofirstdemo.component.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface TeacherComponent {

  TeacherResponse createTeacher(final TeacherRequest request);

  @Getter
  @Setter
  class TeacherResponse {

    private long teacherId;

    private String firstName;

    private String lastName;

    private String nickName;

    private Comment comment;

    private String city;

  }

  @Getter
  @Setter
  class TeacherRequest {

    private String firstName;

    private String lastName;

    private String nickName;

    private String password;

    private Comment comment;

    private double lat;

    private double lon;

    private String street;

    private String number;

    private String city;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class Comment {

    private String comment;
  }
}
