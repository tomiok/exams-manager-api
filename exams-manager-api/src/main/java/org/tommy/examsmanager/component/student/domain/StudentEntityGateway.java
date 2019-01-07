package org.tommy.examsmanager.component.student.domain;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoOperations;
import org.tommy.examsmanager.application.cipher.ApplicationCipher;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;

public class StudentEntityGateway {

  private final StudentRepository studentRepository;

  private final ApplicationCipher applicationCipher;

  private final MongoOperations ops;

  public StudentEntityGateway(final StudentRepository studentRepository,
                              final ApplicationCipher applicationCipher,
                              final MongoOperations mongoOperations) {
    this.studentRepository = studentRepository;
    this.applicationCipher = applicationCipher;
    this.ops = mongoOperations;
  }

  public Student saveStudent(final Student student) {
    Optional<String> pass = Optional.ofNullable(student.getPassword());
    pass.ifPresent(s -> student.setPassword(applicationCipher.encrypt(s)));
    return studentRepository.save(student);
  }

  public Student findById(final String id) {
    return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Student findByEmail(final String email) {
    List<Student> students = ops.find(query(where("email")
        .is(email)
        .and("profileVisible")
        .is(true)), Student.class);

    if (students.isEmpty()) {
      throw new EntityNotFoundException("The student with email %s is not in the database,"
                                        + " or is not visible for the public", email);
    }

    return students.get(0);
  }

  public boolean updateVisibility(final String id, final boolean visible) {
    Student s = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    s.setProfileVisible(visible);

    //update student
    studentRepository.save(s);
    return visible;
  }
}
