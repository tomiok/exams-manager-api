package org.tommy.mongofirstdemo.domain.techer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, Long> {
}
