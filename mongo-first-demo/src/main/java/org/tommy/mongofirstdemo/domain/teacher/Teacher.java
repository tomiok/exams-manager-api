package org.tommy.mongofirstdemo.domain.teacher;

import java.util.List;
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
  private String id;

  private String firstName;

  private String lastName;

  @Indexed(unique = true)
  private String nickName;

  private String password;

  private Address address;

  private Comment comment;

  private List<Signature> signatures;

  private Teacher() {}

  private Teacher(final String firstName, final String lastName, final String nickName, final String password,
                  final Address address, final Comment comment) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.password = password;
    this.address = address;
    this.comment = comment;
  }

  public static Teacher from(TeacherComponent.TeacherRequest request) {
    return new Teacher(request.getFirstName(), request.getLastName(),
        request.getNickName(), request.getPassword(),
        Address.from(request.getLat(), request.getLon(), request.getStreet(), request.getCity(), request.getNumber()),
        new Comment(request.getComment().getComment()));
  }
}
