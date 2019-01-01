package org.tommy.examsmanager.component.shared;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException() {
    super("The entity is not in the database.");
  }
}
