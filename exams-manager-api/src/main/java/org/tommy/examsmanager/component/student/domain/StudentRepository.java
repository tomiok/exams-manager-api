package org.tommy.examsmanager.component.student.domain;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

  Optional<Student> findByEmail(String email);
}
