package org.tommy.examsmanager.component.exam.domain;

import java.time.LocalDate;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tommy.examsmanager.component.college.domain.College;
import org.tommy.examsmanager.component.college.usecase.SaveCollegeService;
import org.tommy.examsmanager.component.exam.usecase.SaveExamService;

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

  private College college;

  private boolean active;

  public Exam(final String signature, final LocalDate date, final String comments, final boolean enrolled) {
    validations(signature, date, comments);
    this.signature = signature;
    this.date = date;
    this.comments = comments;
    this.enrolled = enrolled;
    this.active = true;
  }

  public Exam(final String signature, final LocalDate date, final String comments) {
    validations(signature, date, comments);
    this.signature = signature;
    this.date = date;
    this.comments = comments;
    this.active = true;
  }

  public Exam(final String signature, final LocalDate date, final boolean enrolled) {
    validations(signature, date, null);
    this.signature = signature;
    this.date = date;
    this.enrolled = enrolled;
    this.active = true;
  }

  public Exam(final String signature, final LocalDate date, final String comments, final boolean enrolled,
              final College college) {

    validations(signature, date, comments);
    this.signature = signature;
    this.date = date;
    this.comments = comments;
    this.enrolled = enrolled;
    this.college = college;
    this.active = true;
  }

  public Exam(final String signature, final LocalDate date, final boolean enrolled,
              final College college) {
    this.signature = signature;
    this.date = date;
    this.enrolled = enrolled;
    this.college = college;
    this.active = true;
  }

  public static Exam fromRequest(final SaveExamService.SaveExamRequest req) {
    Optional<LocalDate> dateOpt = Optional.ofNullable(req.getDate());
    Optional<SaveCollegeService.CollegeReq> collOpt = Optional.ofNullable(req.getCollegeReq());

    College college = collOpt.map(r ->
        new College(r.getCollegeName(), r.getCollegeAddress()))
        .orElse(null);

    return new Exam(req.getSignature(), dateOpt.orElse(null), req.getComments(), req.getEnrolled(),
        college);

  }

  private void validations(String signature, LocalDate date, String comments) {
    Validate.notBlank(signature);
    if (date != null) {
      Validate.isTrue(date.isAfter(LocalDate.now()), "The exam should be at least one day after today");
    }
    Optional.ofNullable(comments).ifPresent(Validate::notBlank);
  }
}
