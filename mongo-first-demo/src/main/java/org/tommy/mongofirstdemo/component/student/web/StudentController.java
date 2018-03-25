package org.tommy.mongofirstdemo.component.student.web;

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
import org.tommy.mongofirstdemo.component.student.StudentComponent;
import org.tommy.mongofirstdemo.shared.WebUtils;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

  private final StudentComponent studentComponent;

  public StudentController(final StudentComponent studentComponent) {
    this.studentComponent = studentComponent;
  }

  @PostMapping
  public ResponseEntity<?> createStudent(@RequestBody final StudentComponent.CreateStudentRequest studentRequest,
                                         final HttpServletRequest httpReq)
      throws Exception {
    String id = studentComponent.registerStudent(studentRequest);
    URI uri = WebUtils.getCreatedEntityUri(id, httpReq);
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentComponent.StudentResponse> findById(@PathVariable("id") final String id) {
    return ok(studentComponent.getStudentById(id));
  }
}
