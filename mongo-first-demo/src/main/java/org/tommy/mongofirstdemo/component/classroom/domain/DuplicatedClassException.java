package org.tommy.mongofirstdemo.component.classroom.domain;

public class DuplicatedClassException extends RuntimeException {

  private static final String MESSAGE = "This class is duplicated, please cancel the old one or wait until"
                                        + "the teacher accept yours.";

  public DuplicatedClassException() {
    super(MESSAGE);
  }
}
