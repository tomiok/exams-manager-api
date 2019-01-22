package org.tommy.examsmanager.component.exam.domain;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Collection;
import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;
import org.tommy.examsmanager.component.student.domain.Student;

public class ExamEntityGateway {

  private final ExamRepository examRepository;

  private final MongoOperations template;

  public ExamEntityGateway(final ExamRepository examRepository,
                           final MongoTemplate template) {
    this.examRepository = examRepository;
    this.template = template;
  }

  public Exam saveExam(Exam e) {
    return examRepository.save(e);
  }

  public List<Exam> saveExams(List<Exam> exams) {
    return examRepository.saveAll(exams);
  }

  public List<Exam> findExamsByStudent(String email) {
    List<Student> students = template.find(Query.query(where("email").is(email)), Student.class);

    return students
        .stream()
        .map(Student::getExams)
        .flatMap(Collection::stream)
        .collect(toList());
  }

  public void dismissExam(String studentId, String examId) {
    Student s = template.findById(studentId, Student.class);
    if (s == null) {
      throw new EntityNotFoundException("Student with ID %s is not present in the database", studentId);
    }

    s.getExams()
        .stream()
        .filter(e -> e.getId().equals(examId))
        .forEach(e -> e.setActive(false));
  }
}
