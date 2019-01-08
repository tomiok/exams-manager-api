package org.tommy.examsmanager.component.college.domain;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoOperations;

public class CollegeEntityGateway {

  private final MongoOperations mongoOps;

  public CollegeEntityGateway(final MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }

  public College save(College college) {
    return mongoOps.save(college);
  }

  public College findByName(String name) {
    College example = new College();
    example.setName(name);
    List<College> colleges = mongoOps
        .find(query(where("name")
            .alike(Example.of(example))), College.class);

    //TODO finish this
    return null;
  }
}
