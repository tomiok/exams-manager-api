package org.tommy.examsmanager.component.college.usecase;

import org.tommy.examsmanager.component.college.domain.College;

public interface SaveCollegeService {

  /**
   *
   * @param c The college to save.
   * @return The college saved.
   */
  College save(College c);
}
