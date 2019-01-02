package org.tommy.examsmanager.component.exam.web;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

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
}
