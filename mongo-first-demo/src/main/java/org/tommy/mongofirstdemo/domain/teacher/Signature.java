package org.tommy.mongofirstdemo.domain.teacher;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "signatures")
@Getter
@Setter
public class Signature {

  @Id
  private String id;

  private String signature;

  private Level level;
}
