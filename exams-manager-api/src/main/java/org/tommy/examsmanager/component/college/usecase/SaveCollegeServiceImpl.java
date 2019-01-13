package org.tommy.examsmanager.component.college.usecase;

import org.tommy.examsmanager.component.college.domain.College;
import org.tommy.examsmanager.component.college.domain.CollegeEntityGateway;

public class SaveCollegeServiceImpl implements SaveCollegeService {

  private final CollegeEntityGateway collegeEntityGateway;

  public SaveCollegeServiceImpl(final CollegeEntityGateway collegeEntityGateway) {
    this.collegeEntityGateway = collegeEntityGateway;
  }

  @Override
  public College save(final String name, final String address) {
    College c = new College(name, address);
    return collegeEntityGateway.save(c);
  }
}
