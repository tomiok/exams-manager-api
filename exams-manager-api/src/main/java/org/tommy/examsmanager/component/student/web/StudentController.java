package org.tommy.examsmanager.component.student.web;

import static org.springframework.http.ResponseEntity.ok;

import io.reactivex.Flowable;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.usecase.FindStudentService;
import org.tommy.examsmanager.component.student.usecase.SaveStudentService;
import org.tommy.examsmanager.component.token.TokenExtractor;
import org.tommy.examsmanager.component.token.TokenFactory;
import org.tommy.examsmanager.shared.web.WebUtils;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

  private final SaveStudentService saveStudentService;

  private final FindStudentService findStudentService;

  private final TokenFactory tokenFactory;

  private final TokenExtractor tokenExtractor;

  public StudentController(final SaveStudentService saveStudentService,
                           final TokenFactory tokenFactory,
                           final FindStudentService findStudentService,
                           final TokenExtractor tokenExtractor) {
    this.saveStudentService = saveStudentService;
    this.tokenFactory = tokenFactory;
    this.findStudentService = findStudentService;
    this.tokenExtractor = tokenExtractor;
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
  public ResponseEntity<HttpStudentResponse> findById(
      @PathVariable("id") final String id,
      HttpServletRequest req
  ) {
    tokenExtractor.validate(WebUtils.getAuthorizationToken(req));
    return ok(new HttpStudentResponse(findStudentService.getStudentById(id)));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<HttpStudentResponse> findByEmail(
      @PathVariable("email") final String email,
      HttpServletRequest req) {
    tokenExtractor.validate(WebUtils.getAuthorizationToken(req));
    return ok(new HttpStudentResponse(findStudentService.getStudentByEmail(email)));
  }

  @PatchMapping("/profile/{visible}")
  public ResponseEntity<UpdateVisibilityHttpReq> updateProfileVisibility(@PathVariable("visible") boolean visible,
                                                                         HttpServletRequest httpReq) {
    String token = WebUtils.getAuthorizationToken(httpReq);
    tokenExtractor.validate(token);
    String studentId = tokenExtractor.getStudentId(token);
    boolean visibleSaved = findStudentService.updateProfileVisibility(studentId, visible);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UpdateVisibilityHttpReq(visibleSaved));
  }

  @GetMapping
  public Flowable<Student> students(
      HttpServletRequest request
  ) {
    String token = WebUtils.getAuthorizationToken(request);
    tokenExtractor.validate(token);
    return findStudentService.getAll();
  }
}