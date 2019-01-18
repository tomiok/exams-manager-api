package org.tommy.examsmanager.component.student.usecase;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import org.tommy.examsmanager.component.student.domain.Student;

public interface FindStudentService {

  Student getStudentById(String id);

  Student getStudentByEmail(String email);

  boolean updateProfileVisibility(String id, boolean visible);

  Flowable<Student> getAll();

  Single<List<Student>> getAllEmitter();
}


