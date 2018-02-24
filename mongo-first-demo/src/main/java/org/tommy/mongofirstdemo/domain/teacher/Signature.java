package org.tommy.mongofirstdemo.domain.teacher;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;

@Document(collection = "signatures")
@NoArgsConstructor
@Getter
@Setter
public class Signature {

  @Id
  private String id;

  private String signature;

  private Level level;

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signature signature1 = (Signature) o;
    return Objects.equals(signature, signature1.signature) &&
           level == signature1.level;
  }

  @Override
  public int hashCode() {

    return Objects.hash(signature, level);
  }

  public Signature(final String signature, final Level level) {
    this.signature = signature;
    this.level = level;
  }

  static Signature from(final TeacherComponent.SignatureDto dto) {
    return new Signature(dto.getSignature(), dto.getLevel());
  }
}
