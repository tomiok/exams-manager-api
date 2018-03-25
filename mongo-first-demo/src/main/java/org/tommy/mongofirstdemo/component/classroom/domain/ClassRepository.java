package org.tommy.mongofirstdemo.component.classroom.domain;

import org.springframework.data.repository.CrudRepository;

public interface ClassRepository extends CrudRepository<ClassRequest, String> {
}
