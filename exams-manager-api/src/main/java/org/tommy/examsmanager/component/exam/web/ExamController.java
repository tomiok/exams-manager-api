package org.tommy.examsmanager.component.exam.web;

import static java.util.stream.Collectors.toList;
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
    tokenExtractor.validate(token);
    String studentId = tokenExtractor.getStudentId(token);

    Exam exam = saveExamService.saveExam(new SaveExamService.SaveExamRequest(studentId, bizReq.getSignature(),
        bizReq.isEnrolled(), LocalDate.parse(bizReq.getDate()), bizReq.getCollegeReq()));

    return ok(exam);
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<Exam>> addExams(
      @RequestBody List<CreateExamHttpReq> bizReqs,
      HttpServletRequest httpReq) {
    String token = WebUtils.getAuthorizationToken(httpReq);
    tokenExtractor.validate(token);
    String studentId = tokenExtractor.getStudentId(token);

    List<SaveExamService.SaveExamRequest> examRequests = bizReqs
        .stream()
        .map(r ->
            new SaveExamService.SaveExamRequest(studentId, r.getSignature(),
                r.isEnrolled(), LocalDate.parse(r.getDate()), r.getCollegeReq()))
        .collect(toList());

    List<Exam> exams = saveExamService.saveExam(examRequests);

    return ok(exams);
  }
}
