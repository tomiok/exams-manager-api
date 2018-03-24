package org.tommy.mongofirstdemo.component.classroom.usecase;

import lombok.Getter;
import lombok.Setter;

public interface RequestClassService {

  void requestClass(final Request request);

  @Getter
  @Setter
  class Request {

    private String studentId;

    private String teacherId;

    private String comment;

  }

}
