package org.tommy.examsmanager.component.student.domain;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.tommy.examsmanager.component.exam.domain.Exam;

public interface StudentRepository extends MongoRepository<Student, String> {

  @Query("")
  List<Exam> findExamsByStudentId();
}
