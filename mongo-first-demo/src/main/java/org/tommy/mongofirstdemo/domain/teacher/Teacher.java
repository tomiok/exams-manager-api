package org.tommy.mongofirstdemo.domain.teacher;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
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
  private String mail;

  private String password;

  private Address address;

  private Comment comment;

  private Set<Signature> signatures;

  private boolean goToStudentHouse;

  private Teacher() {}

  private Teacher(final String firstName, final String lastName, final String mail, final String password,
                  final Address address, final Comment comment, final Set<Signature> signatures,
                  final boolean goToStudentHouse) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.mail = mail;
    this.password = password;
    this.address = address;
    this.comment = comment;
    this.signatures = signatures;
    this.goToStudentHouse = goToStudentHouse;
  }

  public static Teacher from(TeacherComponent.TeacherRequest request) {
    return new Teacher(request.getFirstName(), request.getLastName(),
        request.getMail(), request.getPassword(),
        Address.from(request.getLat(), request.getLon(), request.getStreet(), request.getCity(), request.getNumber()),
        new Comment(request.getComment().getComment()),
        request
            .getSignatures()
            .stream()
            .map(Signature::from)
            .collect(toSet()), request.isGoToStudentHouse());
  }

}
