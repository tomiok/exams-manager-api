package org.tommy.mongofirstdemo.component.shared;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException() {
    super("The entity is not in the database.");
  }
}
