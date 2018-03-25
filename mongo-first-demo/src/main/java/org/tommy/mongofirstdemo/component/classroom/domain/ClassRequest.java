package org.tommy.mongofirstdemo.component.classroom.domain;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;

@Document(collection = "classRequest")
@Getter
@Setter
public class ClassRequest {

  @Id
  private String id;

  private Student student;

  private Teacher teacher;

  private String comment;

  private ClassStatus status;

  private Instant dateCreated;

  @Version
  private Long version;

  public ClassRequest(final Student student, final Teacher teacher, final String comment,
                      final ClassStatus status, final Instant dateCreated) {
    this.student = student;
    this.teacher = teacher;
    this.comment = comment;
    this.status = status;
    this.dateCreated = dateCreated;
  }
}
