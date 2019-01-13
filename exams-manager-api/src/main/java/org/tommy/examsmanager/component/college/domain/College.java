package org.tommy.examsmanager.component.college.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "college")
@Getter
@Setter
@NoArgsConstructor
public class College {

  @Id
  private String id;

  private String name;

  private String address;

  public College(final String name, final String address) {
    Validate.notBlank(name, "The name cannot be null or blank");
    this.name = name;
    this.address = address;
  }
}
