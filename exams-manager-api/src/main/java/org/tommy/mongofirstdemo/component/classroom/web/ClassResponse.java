package org.tommy.mongofirstdemo.component.classroom.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;

@Getter
@AllArgsConstructor
class ClassResponse {

  private Student student;

  private Teacher teacher;

  private String comment;

  private ClassStatus classStatus;


  static ClassResponse from(final ClassRequest classRequest) {

    Student student = new Student(classRequest.getStudent());
    Teacher teacher = new Teacher(classRequest.getTeacher());

    return new ClassResponse(student, teacher, classRequest.getComment(), classRequest.getStatus());
  }

  @Getter
  static class Student {

    private String id;

    private String firstName;

    private String lastName;

    Student(final org.tommy.mongofirstdemo.component.student.domain.Student student) {
      this.id = student.getId();
      this.firstName = student.getFirstName();
      this.lastName = student.getLastName();
    }
  }

  @Getter
  static class Teacher {

    private String id;

    private String firstName;

    private String lastName;

    Teacher(final org.tommy.mongofirstdemo.component.teacher.domain.Teacher teacher) {
      this.id = teacher.getId();
      this.firstName = teacher.getFirstName();
      this.lastName = teacher.getLastName();
    }
  }
}
