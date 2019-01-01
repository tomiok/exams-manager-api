package org.tommy.examsmanager.component.exam.web;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.usecase.SaveExamService;
import org.tommy.examsmanager.component.token.TokenExtractor;
import org.tommy.examsmanager.shared.WebUtils;

@RestController
@RequestMapping("/exams")
public class ExamController {

  private final SaveExamService saveExamService;

  private final TokenExtractor tokenExtractor;

  public ExamController(final SaveExamService saveExamService,
                        final TokenExtractor tokenExtractor) {
    this.saveExamService = saveExamService;
    this.tokenExtractor = tokenExtractor;
  }

  @PostMapping
  public ResponseEntity<?> addExam(@RequestBody CreateExamHttpReq bizReq,
                                   HttpServletRequest httpReq) {
    String token = WebUtils.getAuthorizationToken(httpReq);
    // String email = tokenExtractor.extract("email", token);
    String studentId = tokenExtractor.getStudentId(token);
    Exam exam = saveExamService.saveExam(new SaveExamService.SaveExamRequest(studentId, bizReq.getSignature(),
        bizReq.isEnrolled(), LocalDate.parse(bizReq.getDate())));

    return ResponseEntity.ok(exam);
  }
}
