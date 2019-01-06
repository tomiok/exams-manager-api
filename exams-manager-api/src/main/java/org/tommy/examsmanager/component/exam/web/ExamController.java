package org.tommy.examsmanager.component.exam.web;

import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.usecase.SaveExamService;
import org.tommy.examsmanager.component.token.TokenExtractor;
import org.tommy.examsmanager.shared.web.WebUtils;

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
  public ResponseEntity<Exam> addExam(@RequestBody CreateExamHttpReq bizReq,
                                      HttpServletRequest httpReq) {
    String token = WebUtils.getAuthorizationToken(httpReq);
    String studentId = tokenExtractor.getStudentId(token);

    //TODO logic to chose the right constructor if comments are coming from the request.
    Exam exam = saveExamService.saveExam(new SaveExamService.SaveExamRequest(studentId, bizReq.getSignature(),
        bizReq.isEnrolled(), LocalDate.parse(bizReq.getDate())));

    return ok(exam);
  }

  @PostMapping("/bulk")
  public ResponseEntity<Exam> addExams(@RequestBody List<CreateExamHttpReq> bizReq,
                                       HttpServletRequest httpReq) {
    String token = WebUtils.getAuthorizationToken(httpReq);
    String studentId = tokenExtractor.getStudentId(token);

    //TODO logic to chose the right constructor if comments are coming from the request.
    //TODO transform biz request in domain request

    //Exam exam = saveExamService.saveExam();

    return ok().build();
  }

}
