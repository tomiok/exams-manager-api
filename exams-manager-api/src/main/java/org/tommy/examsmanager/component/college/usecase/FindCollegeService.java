package org.tommy.examsmanager.component.college.usecase;

import java.util.List;
import org.tommy.examsmanager.component.college.domain.College;

public interface FindCollegeService {

  List<College> findCollegesByName(String name);

  College findById(String id);
}
