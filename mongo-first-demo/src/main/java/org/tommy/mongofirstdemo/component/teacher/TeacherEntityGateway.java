package org.tommy.mongofirstdemo.component.teacher;

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
    return new TeacherComponent.TeacherResponse(repository.findOne(id));
  }

  TeacherComponent.TeacherResponse transform(final TeacherComponent.TeacherRequest request) {
    TeacherComponent.TeacherResponse response = new TeacherComponent.TeacherResponse();

    Teacher teacher = save(request);
    TeacherComponent.Comment comment = new TeacherComponent.Comment(teacher.getComment().getDescription());

    response.setCity(teacher.getAddress().getCity());
    response.setComment(comment);
    response.setFirstName(teacher.getFirstName());
    response.setLastName(teacher.getLastName());
    response.setNickName(teacher.getNickName());
    response.setTeacherId(teacher.getId());

    return response;
  }

  private Teacher transformToModel(final TeacherComponent.TeacherRequest request) {
    return Teacher.from(request);
  }

  private Teacher save(final TeacherComponent.TeacherRequest request) {
    repository.deleteAll(); //TODO remove this some time in the future

    return repository.save(transformToModel(request));
  }
}
