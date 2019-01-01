package org.tommy.examsmanager.component.exam.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
}
