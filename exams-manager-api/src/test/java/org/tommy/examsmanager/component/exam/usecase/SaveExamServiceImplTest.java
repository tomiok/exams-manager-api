package org.tommy.examsmanager.component.exam.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

@RunWith(MockitoJUnitRunner.class)
public class SaveExamServiceImplTest {

  @Mock
  private ExamEntityGateway examEntityGateway;

  @Mock
  private StudentEntityGateway studentEntityGateway;

  @InjectMocks
  private SaveExamServiceImpl saveExamService;

  @Test
  public void saveExam() {

    String studentId = "someId";
    String signature = "History";
    LocalDate now = LocalDate.now();

    SaveExamService.SaveExamRequest request =
        createRequest(studentId, signature, "some comments",
            false, now.plusDays(9));

    when(examEntityGateway.saveExam(any(Exam.class))).thenReturn(
        new Exam(signature, now.plusDays(9), "some comments", false));

    when(studentEntityGateway.findById(studentId)).thenReturn(new Student());

    Exam e = saveExamService.saveExam(request);

    assertThat(e.getSignature()).isEqualTo(signature);
    assertThat(e.getComments()).isEqualTo("some comments");
    assertThat(e.isEnrolled()).isFalse();
  }

  private SaveExamService.SaveExamRequest createRequest(
      String studentId,
      String signature,
      String comments,
      Boolean enrolled,
      LocalDate localDate
  ) {
    return new SaveExamService.SaveExamRequest(
        studentId, signature, comments, enrolled, localDate);
  }
}