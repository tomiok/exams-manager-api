package org.tommy.mongofirstdemo.component.teacher;

import static java.util.Optional.ofNullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;

public interface TeacherComponent {

  TeacherResponse createTeacher(final TeacherRequest request);

  TeacherResponse findById(final String id);

  @Getter
  @Setter
  @NoArgsConstructor
  class TeacherResponse {

    private String teacherId;

    private String firstName;

    private String lastName;

    private String nickName;

    private Comment comment;

    private String city;

    TeacherResponse(final Teacher teacher) {
      this.teacherId = teacher.getId();
      this.firstName = teacher.getFirstName();
      this.lastName = teacher.getLastName();
      this.nickName = teacher.getNickName();
      ofNullable(teacher.getComment()).ifPresent(comment -> this.comment = new Comment(comment.getDescription()));
      ofNullable(teacher.getAddress()).ifPresent(address -> this.city = address.getCity());
    }

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
