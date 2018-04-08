package org.tommy.mongofirstdemo.component.student;

import org.tommy.mongofirstdemo.component.student.domain.Student;

public final class StudentMother {

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
