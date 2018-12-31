package org.tommy.mongofirstdemo.component.classroom.usecase;

import lombok.Getter;
import lombok.Setter;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;

public interface RequestClassService {

  ClassRequest requestClass(final Request request);

  @Getter
  @Setter
  class Request {

    private String studentId;

    private String teacherId;

    private String comment;

  }
}
