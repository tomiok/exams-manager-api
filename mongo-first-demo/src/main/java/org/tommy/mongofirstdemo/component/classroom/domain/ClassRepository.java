package org.tommy.mongofirstdemo.component.classroom.domain;

import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClassRepository extends MongoRepository<ClassRequest, String> {

  @Query("{'student.id': ?0}")
  Set<ClassRequest> findByStudentId(final ObjectId studentId);

  @Query("{'teacher.id': ?0}")
  Set<ClassRequest> findByTeacherId(final ObjectId teacherId);

  @Query("{ $and: [{'student.id': ?0}, {'teacher.id': ?1}, {'status': 'PENDING'}] }")
  ClassRequest findByStudentIdAndTeacherId(final ObjectId sId, final ObjectId tId);
}

