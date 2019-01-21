package org.tommy.examsmanager.component.college.usecase;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tommy.examsmanager.component.college.domain.College;
import org.tommy.examsmanager.component.college.domain.CollegeEntityGateway;

public class FindCollegeServiceImpl implements FindCollegeService {

  private static final Logger log = LoggerFactory.getLogger(FindCollegeServiceImpl.class);

  private final CollegeEntityGateway collegeEntityGateway;

  FindCollegeServiceImpl(final CollegeEntityGateway collegeEntityGateway) {
    this.collegeEntityGateway = collegeEntityGateway;
  }

  @Override
  public List<College> findCollegesByName(final String name) {
    return collegeEntityGateway.findByName(name);
  }

  @Override
  public College findById(final String id) {
    log.info("Fetching info from college id %s", id);

    return collegeEntityGateway.findById(id);
  }
}
