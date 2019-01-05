package org.tommy.examsmanager;

import java.util.List;
import org.tommy.examsmanager.component.exam.domain.Exam;
import org.tommy.examsmanager.component.student.domain.Student;

public final class TestFixtures {

  public static Student createStudent(String email, String firstName,
                                      String lastName, String password) {
    Student student = new Student();

    student.setEmail(email);
    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setPassword(password);

    return student;
  }

  public static void addExams(List<Exam> exams, Student student) {
    student.setExams(exams);
  }

  public static Student createAlan() {
    return new Student("Alan", "Turing", "passsss", "alan@apple.com");
  }

  public static Student createUncle() {
    return new Student("Robert", "Martin", "passsss", "robert@cleancode.com");
  }

  public static Student createFowler() {
    return new Student("Martin", "Fowler", "passsss", "martinf@apple.com");
  }

  public static Student createCapote() {
    return new Student("Vicente", "De la mata", "passsss", "vicente@charrua.com");
  }
}
