package org.tommy.mongofirstdemo.component.teacher;

import java.util.Set;
import org.tommy.mongofirstdemo.domain.Address;
import org.tommy.mongofirstdemo.domain.teacher.Comment;
import org.tommy.mongofirstdemo.domain.teacher.Signature;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;

public class TeacherMother {

  public static TeacherComponent.TeacherRequest
  createRequest(final TeacherComponent.Comment comment, final String firstName,
                final double lat, final  double lon) {
    TeacherComponent.TeacherRequest request = new TeacherComponent.TeacherRequest();

    request.setFirstName(firstName);
    request.setLastName("Simpson");
    request.setComment(comment);
    request.setLat(lat);
    request.setLon(lon);
    request.setMail("some_cool_nick");
    request.setCity("Rosario");

    return request;
  }

  public static TeacherComponent.Comment createComment(final String comment) {
    TeacherComponent.Comment comm = new TeacherComponent.Comment();
    comm.setComment(comment);
    return comm;
  }

  public static Teacher createTeacher(final String firstName, final String lastName, final Address address,
                                      final Set<Signature> signatures, final Comment comment) {
    return new Teacher("id", firstName, lastName, "tomi@msn.com", "1234", address, comment,
        signatures, false);
  }
}
