package org.tommy.examsmanager.component.exam.usecase;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.tommy.examsmanager.component.exam.domain.Exam;

public interface SaveExamService {

  Exam saveExam(SaveExamRequest req);

  @Getter
  @Setter
  class SaveExamRequest {

    private String studentId;
    private String signature;
    private String comments;
    private Boolean enrolled;
    private LocalDate date;

    public SaveExamRequest(final String studentId, final String signature, final String comments,
                           final Boolean enrolled, final LocalDate date) {
      this.studentId = studentId;
      this.signature = signature;
      this.comments = comments;
      this.enrolled = enrolled;
      this.date = date;
    }

    public SaveExamRequest(final String studentId, final String signature, final Boolean enrolled,
                           final LocalDate date) {
      this.studentId = studentId;
      this.signature = signature;
      this.enrolled = enrolled;
      this.date = date;
    }
  }
}