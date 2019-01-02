package org.tommy.examsmanager.component.exam.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exam")
@Getter
@Setter
@NoArgsConstructor
public class Exam {

  @Id
  private String id;

  private String signature;

  private LocalDate date;

  private String comments;

  private boolean enrolled;

  public Exam(final String signature, final LocalDate date, final String comments, final boolean enrolled) {
    validations(signature, date);
    this.signature = signature;
    this.date = date;
    this.comments = comments;
    this.enrolled = enrolled;
  }

  public Exam(final String signature, final LocalDate date, final String comments) {
    validations(signature, date);
    this.signature = signature;
    this.date = date;
    this.comments = comments;
  }

  public Exam(final String signature, final LocalDate date, final boolean enrolled) {
    validations(signature, date);
    this.signature = signature;
    this.date = date;
    this.enrolled = enrolled;
  }

  private void validations(String signature, LocalDate date) {
    Validate.notBlank(signature);
    Validate.isTrue(date.isAfter(LocalDate.now()), "The exam should be at least one day after today");
  }
}
