package org.tommy.examsmanager.component.exam.usecase;

import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

public class SaveExamServiceImpl implements SaveExamService {

  private ExamEntityGateway examEntityGateway;

  private final StudentEntityGateway studentEntityGateway;

  SaveExamServiceImpl(final ExamEntityGateway examEntityGateway,
                      final StudentEntityGateway studentEntityGateway) {
    this.examEntityGateway = examEntityGateway;
    this.studentEntityGateway = studentEntityGateway;
  }

  @Override
  public Exam saveExam(final SaveExamRequest req) {
    Exam e = new Exam(req.getSignature(), req.getDate(), req.getEnrolled());

    Exam examSaved = examEntityGateway.saveExam(e);
    Student s = studentEntityGateway.findById(req.getStudentId());
    s.addExam(examSaved);

    //update student with the new exam
    studentEntityGateway.saveStudent(s);

    return examSaved;
  }
}
