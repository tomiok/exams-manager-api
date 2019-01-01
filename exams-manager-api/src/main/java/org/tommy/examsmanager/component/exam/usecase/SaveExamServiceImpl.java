package org.tommy.examsmanager.component.exam.usecase;

import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.exam.domain.ExamRepository;

public class SaveExamServiceImpl implements SaveExamService {

  private ExamEntityGateway examEntityGateway;

  @Override
  public Exam saveExam(final SaveExamRequest req) {
    return null;
  }
}
