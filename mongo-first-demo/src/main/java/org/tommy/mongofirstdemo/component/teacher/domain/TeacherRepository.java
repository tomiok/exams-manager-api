package org.tommy.mongofirstdemo.component.teacher.domain;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

  @Query(value = "{ 'signatures.signature' : {$regex : ?0} , 'signatures.level' : ?1 }")
  Set<Teacher> findBySignatureCommand(final String signature, final Level level);
}
