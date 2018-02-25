package org.tommy.mongofirstdemo.component.teacher.usecase;

import static java.util.stream.Collectors.toSet;

import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.component.teacher.TeacherEntityGateway;
import org.tommy.mongofirstdemo.domain.Address;
import org.tommy.mongofirstdemo.domain.teacher.Comment;
import org.tommy.mongofirstdemo.domain.teacher.Signature;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;

public class UpdateTeacherImpl implements Update {

  private final TeacherEntityGateway teacherEntityGateway;

  public UpdateTeacherImpl(final TeacherEntityGateway teacherEntityGateway) {
    this.teacherEntityGateway = teacherEntityGateway;
  }

  @Override
  public void updatePersonalInformation(final PersonalInfoRequest request) {
    Validate.notNull(request.getTeacherId(), "teacherId cannot be null");
    Validate.notNull(request.getFirstName(), "firstName cannot be null");
    Validate.notNull(request.getLastName(), "lastName cannot be null");
    Validate.notNull(request.getEmail(), "email cannot be null");
    Validate.notNull(request.getComment(), "comment cannot be null");

    Teacher teacher = getTeacherById(request.getTeacherId());
    teacher.setFirstName(request.getFirstName());
    teacher.setLastName(request.getLastName());
    teacher.setMail(request.getEmail());
    teacher.setComment(new Comment(request.getComment()));

    teacherEntityGateway.saveTeacher(teacher);
  }

  @Override
  public void updateSignatures(final SignaturesRequest request) {
    Validate.notNull(request.getTeacherId(), "teacherId cannot be null");
    Validate.isTrue(!request.getSignatures().isEmpty(), "signatures cannot be empty");

    Teacher teacher = getTeacherById(request.getTeacherId());
    teacher.setSignatures(request.getSignatures()
        .stream()
        .map(dto -> new Signature(dto.getSignature(), dto.getLevel()))
        .collect(toSet()));
    teacherEntityGateway.saveTeacher(teacher);
  }

  @Override
  public void updateLocation(final LocationRequest request) {
    Validate.notNull(request.getTeacherId(), "teacherId cannot be null");
    Validate.notNull(request.getCity(), "The city cannot be empty");
    Validate.notNull(request.getStreet(), "The street cannot be empty");
    Validate.notNull(request.getNumber(), "The number cannot be empty");
    Teacher teacher = getTeacherById(request.getTeacherId());
    teacher.setAddress(new Address(request.getLat(), request.getLon(), request.getStreet(), request.getNumber(),
        request.getCity()));
    teacherEntityGateway.saveTeacher(teacher);
  }

  private Teacher getTeacherById(final String id) {
    return teacherEntityGateway.findTeacherById(id);
  }
}
