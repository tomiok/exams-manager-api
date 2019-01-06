package org.tommy.examsmanager.component.student.web;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.util.Optional;
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

  @PostMapping
  public ResponseEntity<HttpStudentResponse> createStudent(
      @RequestBody final SaveStudentService.CreateStudentRequest studentRequest,
      final HttpServletRequest httpReq) {
    Student student = saveStudentService.registerStudent(studentRequest);
    String studentId = student.getId();
    String email = studentRequest.getEmail();
    URI idUri = WebUtils.getCreatedEntityUri(studentId, httpReq);
    return ResponseEntity
        .created(idUri)
        .header("token", tokenFactory.create(studentId, email))
        .body(new HttpStudentResponse(student));
  }

  @GetMapping("/{id}")
  public ResponseEntity<HttpStudentResponse> findById(@PathVariable("id") final String id) {
    return ok(new HttpStudentResponse(findStudentService.getStudentById(id)));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<HttpStudentResponse> findByEmail(@PathVariable("email") final String email) {
    return ok(new HttpStudentResponse(findStudentService.getStudentByEmail(email)));
  }
}