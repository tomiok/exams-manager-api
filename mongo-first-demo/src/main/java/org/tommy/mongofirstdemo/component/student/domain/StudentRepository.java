package org.tommy.mongofirstdemo.component.student.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}
