package org.tommy.examsmanager.component.exam.usecase;

import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;

public class DismissExamServiceImpl implements DismissExamService {

  private final ExamEntityGateway examEntityGateway;

  public DismissExamServiceImpl(final ExamEntityGateway examEntityGateway) {
    this.examEntityGateway = examEntityGateway;
  }

  @Override
  public void dismiss(final String studentId, final String examId) {
    
  }
}
