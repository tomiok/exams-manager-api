package org.tommy.examsmanager.component.student.usecase;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import java.util.concurrent.Callable;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

public class FindStudentServiceImpl implements FindStudentService {

  private final StudentEntityGateway entityGateway;

  FindStudentServiceImpl(final StudentEntityGateway entityGateway) {
    this.entityGateway = entityGateway;
  }

  @Override
  public Student getStudentById(final String id) {
    return entityGateway.findById(id);
  }

  @Override
  public Student getStudentByEmail(final String email) {
    return entityGateway.findByEmail(email);
  }

  @Override
  public boolean updateProfileVisibility(final String id, final boolean visible) {
    return entityGateway.updateVisibility(id, visible);
  }

  @Override
  public Flowable<Student> getAll() {
    return Flowable
        .fromCallable(entityGateway::findAll)
        .parallel(10)
        .sequential()
        .flatMapIterable(e -> e);
  }

  @Override
  public Single<List<Student>> getAllEmitter() {
    Observable<List<Student>> s = Observable.create(
        emitter -> emitter.onNext(entityGateway.findAll())
    );
    return s.singleOrError();
  }
}
