package org.tommy.examsmanager.component.college.usecase;

import org.tommy.examsmanager.component.college.domain.College;
import org.tommy.examsmanager.component.college.domain.CollegeEntityGateway;

public class SaveCollegeServiceImpl implements SaveCollegeService {

  private final CollegeEntityGateway collegeEntityGateway;

  public SaveCollegeServiceImpl(final CollegeEntityGateway collegeEntityGateway) {
    this.collegeEntityGateway = collegeEntityGateway;
  }

  @Override
  public College save(final College c) {
    return collegeEntityGateway.save(c);
  }
}
