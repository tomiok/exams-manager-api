package org.tommy.examsmanager.component.exam.usecase;

import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;

public class SaveExamServiceImpl implements SaveExamService {

  private ExamEntityGateway examEntityGateway;

  SaveExamServiceImpl(final ExamEntityGateway examEntityGateway) {
    this.examEntityGateway = examEntityGateway;
  }

  @Override
  public Exam saveExam(final SaveExamRequest req) {
    return null;
  }
}
