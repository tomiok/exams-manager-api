package org.tommy.examsmanager.component.college.usecase;

import org.tommy.examsmanager.component.college.domain.College;

public interface SaveCollegeService {

  /**
   *
   * @param name The name of the College.
   * @param address The address of the college
   * @return The college saved.
   */
  College save(String name, String address);
}
