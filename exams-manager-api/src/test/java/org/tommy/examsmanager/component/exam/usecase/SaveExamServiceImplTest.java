package org.tommy.examsmanager.component.exam.usecase;

import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
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