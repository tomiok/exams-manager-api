package org.tommy.examsmanager.component.student.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.student.usecase.FindStudentService;
import org.tommy.examsmanager.component.token.TokenExtractor;
import org.tommy.examsmanager.shared.web.WebUtils;

@RestController
@RequestMapping("/profile")
public class ProfileController {

  private final TokenExtractor tokenExtractor;

  private final FindStudentService findStudentService;

  public ProfileController(final TokenExtractor tokenExtractor,
                           final FindStudentService findStudentService) {
    this.tokenExtractor = tokenExtractor;
    this.findStudentService = findStudentService;
  }

  @PatchMapping("/{visible}")
  public ResponseEntity<UpdateVisibilityHttpReq> updateProfileVisibility(@PathVariable("visible") boolean visible,
                                                                         HttpServletRequest httpReq) {
    String studentId = validateTokenAndGetStudentId(httpReq, tokenExtractor);
    boolean visibleSaved = findStudentService.updateProfileVisibility(studentId, visible);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UpdateVisibilityHttpReq(visibleSaved));
  }

  @PatchMapping("/dismiss/exam/{examId}")
  public ResponseEntity<?> dismissExam(
      @PathVariable
      HttpServletRequest httpReq
  ) {
    String studentId = validateTokenAndGetStudentId(httpReq, tokenExtractor);
    return null;
  }

  private static String validateTokenAndGetStudentId(
      HttpServletRequest request,
      TokenExtractor tokenExtractor
  ) {
    String token = WebUtils.getAuthorizationToken(request);
    tokenExtractor.validate(token);
    return tokenExtractor.getStudentId(token);
  }

  //TODO finish APIs here. dismiss exam - see profile - see exams (filters) - dismiss exams with a background service
}
