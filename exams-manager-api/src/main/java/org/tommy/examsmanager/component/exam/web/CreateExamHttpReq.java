package org.tommy.examsmanager.component.exam.web;

import java.time.LocalDate;
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

  private String date;

  private LocalDate localDate;
}
