package org.tommy.examsmanager.component.student.web;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.shared.UserType;
import org.tommy.examsmanager.component.student.StudentComponent;
import org.tommy.examsmanager.component.token.TokenFactory;
import org.tommy.examsmanager.shared.WebUtils;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

  private final StudentComponent studentComponent;

  private final TokenFactory tokenFactory;

  public StudentController(final StudentComponent studentComponent,
                           final TokenFactory tokenFactory) {
    this.studentComponent = studentComponent;
    this.tokenFactory = tokenFactory;
  }

  @PostMapping
  public ResponseEntity<?> createStudent(@RequestBody final StudentComponent.CreateStudentRequest studentRequest,
                                         final HttpServletRequest httpReq) {
    String id = studentComponent.registerStudent(studentRequest);
    URI uri = WebUtils.getCreatedEntityUri(id, httpReq);
    return ResponseEntity.created(uri).header("token", tokenFactory.create(id, UserType.STUDENT)).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentComponent.StudentResponse> findById(@PathVariable("id") final String id) {
    return ok(studentComponent.getStudentById(id));
  }
}