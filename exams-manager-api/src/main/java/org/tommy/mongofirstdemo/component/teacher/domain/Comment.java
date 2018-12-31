package org.tommy.mongofirstdemo.component.teacher.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
@Getter
@Setter
public class Comment {

  @Id
  private String id;

  @Length(min = 15, max = 250)
  private String description;

  private Comment() {}

  public Comment(final String description) {
    this.description = description;
  }
}
