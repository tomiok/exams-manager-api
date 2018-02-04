package org.tommy.mongofirstdemo.domain.techer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teacher")
@Getter
@Setter
class Teacher {

  @Id
  private long id;

  private String firstName;

  private String lastName;

  @Indexed(unique = true)
  private String nickName;

  private String password;

  private Comment comment;

}
