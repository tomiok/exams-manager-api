package org.tommy.mongofirstdemo.component.teacher;

import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;
import org.tommy.mongofirstdemo.domain.teacher.TeacherRepository;

public class TeacherEntityGateway {

  private final TeacherRepository repository;

  TeacherEntityGateway(final TeacherRepository repository) {
    Validate.notNull(repository, "repository bean cannot be null");
    this.repository = repository;
  }

  TeacherComponent.TeacherResponse findById(final String id) {

    return new TeacherComponent.TeacherResponse(findTeacherById(id));
  }

  TeacherComponent.TeacherResponse transform(final TeacherComponent.TeacherRequest request) {
    Teacher teacher = save(request);
    return new TeacherComponent.TeacherResponse(teacher);
  }

  public Teacher findTeacherById(final String id) {
    Optional<Teacher> teacherOptional = repository.findById(id);
    return teacherOptional.orElseThrow(() -> new IllegalArgumentException("Teacher not found with id " + id));
  }

  public void saveTeacher(final Teacher teacher) {
    repository.save(teacher);
  }

  private Teacher transformToModel(final TeacherComponent.TeacherRequest request) {
    return Teacher.from(request);
  }

  private Teacher save(final TeacherComponent.TeacherRequest request) {
    repository.deleteAll(); //TODO remove this some time in the future

    return repository.save(transformToModel(request));
  }
}
