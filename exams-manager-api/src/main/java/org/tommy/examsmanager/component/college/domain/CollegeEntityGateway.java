package org.tommy.examsmanager.component.college.domain;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;

public class CollegeEntityGateway {

  private static final Logger log = LoggerFactory.getLogger(CollegeEntityGateway.class);

  private final MongoOperations mongoOps;

  public CollegeEntityGateway(final MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }

  public College save(College college) {
    return mongoOps.save(college);
  }

  public List<College> findByName(String name) {
    College example = new College();
    example.setName(name);
    List<College> colleges = mongoOps
        .find(query(where("name")
            .alike(Example.of(example))), College.class);

    log.info("Founded %i colleges", colleges.size());
    return colleges;
  }

  private College findUniqueResult(List<College> founds, String name) {
    if (founds.isEmpty()) {
      throw new EntityNotFoundException("College not found", name);
    }

    if (founds.size() > 1) {
      throw new DataIntegrityViolationException("For an unknown reason, founded "
                                                + " more than one College with name "
                                                + name + ".");
    }

    return founds.get(0);
  }
}
