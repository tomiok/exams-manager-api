package org.tommy.mongofirstdemo.component.teacher;

import org.tommy.mongofirstdemo.domain.techer.Teacher;
import org.tommy.mongofirstdemo.domain.techer.TeacherRepository;

public class TeacherEntityGateway {

  private final TeacherRepository repository;

  public TeacherEntityGateway(final TeacherRepository repository) {
    this.repository = repository;
  }

  public TeacherComponent.TeacherResponse transform(final TeacherComponent.TeacherRequest request) {
    TeacherComponent.TeacherResponse response = new TeacherComponent.TeacherResponse();

    Teacher teacher = save(request);
    TeacherComponent.Comment comment = new TeacherComponent.Comment(teacher.getComment().getDescription());

    response.setCity(teacher.getAddress().getCity());
    response.setComment(comment);
    response.setFirstName(teacher.getFirstName());
    response.setLastName(teacher.getLastName());
    response.setNickName(teacher.getNickName());

    return response;
  }

  private Teacher transformToModel(final TeacherComponent.TeacherRequest request) {
    return Teacher.from(request);
  }

  private Teacher save(final TeacherComponent.TeacherRequest request) {
    return repository.save(transformToModel(request));
  }
}
