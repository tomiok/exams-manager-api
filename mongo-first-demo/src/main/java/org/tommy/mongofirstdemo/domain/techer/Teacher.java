package org.tommy.mongofirstdemo.domain.techer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;
import org.tommy.mongofirstdemo.domain.Address;

@Document(collection = "teacher")
@AllArgsConstructor
@Getter
@Setter
public class Teacher {

  @Id
  private long id;

  private String firstName;

  private String lastName;

  @Indexed(unique = true)
  private String nickName;

  private String password;

  private Address address;

  private Comment comment;

  private Teacher() {}

  public static Teacher from(TeacherComponent.TeacherRequest request) {
    return new Teacher(0, request.getFirstName(), request.getLastName(),
        request.getNickName(), request.getPassword(),
        Address.from(request.getLat(), request.getLon(), request.getStreet(), request.getCity(), request.getNumber()),
        new Comment(request.getComment().getComment()));
  }
}
