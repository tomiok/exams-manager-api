package org.tommy.examsmanager.component.exam.usecase;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.exam.domain.ExamEntityGateway;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

public class SaveExamServiceImpl implements SaveExamService {

  private final StudentEntityGateway studentEntityGateway;
  private ExamEntityGateway examEntityGateway;

  SaveExamServiceImpl(final ExamEntityGateway examEntityGateway,
                      final StudentEntityGateway studentEntityGateway) {
    this.examEntityGateway = examEntityGateway;
    this.studentEntityGateway = studentEntityGateway;
  }

  @Override
  public Exam saveExam(final SaveExamRequest req) {
    Exam e = Exam.fromRequest(req);

    Exam examSaved = examEntityGateway.saveExam(e);
    Student s = studentEntityGateway.findById(req.getStudentId());
    s.addExam(examSaved);

    //update student with the new exam
    studentEntityGateway.saveStudent(s);

    return examSaved;
  }

  @Override
  public List<Exam> saveExam(final List<SaveExamRequest> requests) {
    String studentId = requests.get(0).getStudentId();
    List<Exam> exams =
        requests
            .stream()
            .map(Exam::fromRequest)
            .collect(toList());

    List<Exam> savedExams = examEntityGateway.saveExams(exams);
    Student s = studentEntityGateway.findById(studentId);
    s.addExam(savedExams);

    //update student with exam
    studentEntityGateway.saveStudent(s);

    return savedExams;
  }
}
