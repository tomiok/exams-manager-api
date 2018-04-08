package org.tommy.mongofirstdemo.component.teacher;

import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.application.cipher.ApplicationCipher;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

public class TeacherEntityGateway {

  private final TeacherRepository repository;

  private final ApplicationCipher applicationCipher;

  public TeacherEntityGateway(final TeacherRepository repository,
                       final ApplicationCipher applicationCipher) {
    Validate.notNull(repository, "repository bean cannot be null");
    Validate.notNull(applicationCipher, "applicationCipher cannot be null");
    this.repository = repository;
    this.applicationCipher = applicationCipher;
  }

  TeacherComponent.TeacherResponse findById(final String id) {

    return new TeacherComponent.TeacherResponse(findTeacherById(id));
  }

  TeacherComponent.TeacherResponse transform(final TeacherComponent.TeacherRequest request) {
    Teacher teacher = save(request);
    return new TeacherComponent.TeacherResponse(teacher);
  }

  public Teacher findTeacherById(final String id) {
    return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher not found with id " + id));
  }

  public void saveTeacher(final Teacher teacher) {
    repository.save(teacher);
  }

  private Teacher transformToModel(final TeacherComponent.TeacherRequest request) {
    validateRequest(request);
    String pass = request.getPassword();
    request.setPassword(applicationCipher.encrypt(pass));
    return Teacher.from(request);
  }

  private Teacher save(final TeacherComponent.TeacherRequest request) {
    repository.deleteAll(); //TODO remove this some time in the future
    return repository.save(transformToModel(request));
  }

  private static void validateRequest(final TeacherComponent.TeacherRequest request) {
    Validate.notNull(request.getEmail(), "email cannot be null");
    Validate.notNull(request.getFirstName(), "firstName cannot be null");
    Validate.notNull(request.getLastName(), "lastName cannot be null");
  }
}
