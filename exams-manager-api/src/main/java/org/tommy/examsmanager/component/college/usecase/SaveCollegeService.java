package org.tommy.examsmanager.component.college.usecase;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.tommy.examsmanager.component.college.domain.College;

public interface SaveCollegeService {

  /**
   *
   * @param name The name of the College.
   * @param address The address of the college
   * @return The college saved.
   */
  College save(String name, String address);

  @Getter
  @Setter
  class CollegeReq {

    @NotBlank
    private String collegeName;

    @NotBlank
    private String collegeAddress;
  }
}
