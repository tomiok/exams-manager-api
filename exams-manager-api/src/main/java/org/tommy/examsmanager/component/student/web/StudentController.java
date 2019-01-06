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
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.usecase.FindStudentService;
import org.tommy.examsmanager.component.student.usecase.SaveStudentService;
import org.tommy.examsmanager.component.token.TokenFactory;
import org.tommy.examsmanager.shared.web.WebUtils;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

  private final SaveStudentService saveStudentService;

  private final FindStudentService findStudentService;

  private final TokenFactory tokenFactory;

  public StudentController(final SaveStudentService saveStudentService,
                           final TokenFactory tokenFactory,
                           final FindStudentService findStudentService) {
    this.saveStudentService = saveStudentService;
    this.tokenFactory = tokenFactory;
    this.findStudentService = findStudentService;
  }

  //TODO add response entity
  @PostMapping
  public ResponseEntity<?> createStudent(@RequestBody final SaveStudentService.CreateStudentRequest studentRequest,
                                         final HttpServletRequest httpReq) {
    Student student = saveStudentService.registerStudent(studentRequest);
    String id = student.getId();
    String email = studentRequest.getEmail();
    URI uri = WebUtils.getCreatedEntityUri(id, httpReq);
    return ResponseEntity.created(uri).header("token", tokenFactory.create(id, email)).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<FindStudentService.StudentResponse> findById(@PathVariable("id") final String id) {
    return ok(findStudentService.getStudentById(id));
  }

  //TODO add get student by email
}