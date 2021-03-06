package org.tommy.examsmanager.component.exam.web;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.tommy.examsmanager.component.college.usecase.SaveCollegeService;

@Getter
@Setter
final class CreateExamHttpReq {

  @NotBlank
  private String signature;

  @Nullable
  private String comments;

  /**
   * with format YYYY-MM-DD
   */
  private String date;

  private boolean enrolled;

  @Valid
  private SaveCollegeService.CollegeReq collegeReq;
}
