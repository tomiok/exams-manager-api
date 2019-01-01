package org.tommy.examsmanager.component.exam.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exams")
@Getter
@Setter
public class Exam {

  @Id
  private String id;

  private String signature;

  private LocalDate date;

  private String comments;

  private boolean enrolled;
}
