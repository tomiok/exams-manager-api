package org.tommy.examsmanager.shared.web;

import com.mongodb.MongoWriteException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tommy.examsmanager.component.shared.EntityNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(MongoWriteException.class)
  public ResponseEntity<?> handleDuplicationsInMongo() {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new HttpErrorResponse("The entity is already in DB"));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleDocNotFound(EntityNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new HttpErrorResponse(e.getMessage()));
  }

  @Getter
  @AllArgsConstructor
  private class HttpErrorResponse {

    private String message;
  }
}
