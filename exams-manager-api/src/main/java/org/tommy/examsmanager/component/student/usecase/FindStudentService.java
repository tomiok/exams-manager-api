package org.tommy.examsmanager.component.student.usecase;

import org.tommy.examsmanager.component.student.domain.Student;

public interface FindStudentService {

  Student getStudentById(String id);

  Student getStudentByEmail(String email);
}


