package org.tommy.mongofirstdemo.component.teacher;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;
import org.tommy.mongofirstdemo.component.shared.Address;
import org.tommy.mongofirstdemo.component.teacher.domain.Comment;
import org.tommy.mongofirstdemo.component.teacher.domain.Level;
import org.tommy.mongofirstdemo.component.teacher.domain.Signature;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;

public class TeacherMother {

  public static TeacherComponent.TeacherRequest
  createRequest(final TeacherComponent.Comment comment, final String firstName,
                final double lat, final double lon) {
    TeacherComponent.TeacherRequest request = new TeacherComponent.TeacherRequest();

    request.setFirstName(firstName);
    request.setLastName("Simpson");
    request.setComment(comment);
    request.setLat(lat);
    request.setLon(lon);
    request.setEmail("some_cool_nick@msn.com");
    request.setCity("Rosario");
    request.setGoToStudentHouse(true);
    request.setSignatures(createSignatureDto("Math", "English"));

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

  public static Set<TeacherComponent.SignatureDto> createSignatureDto(final String... signatures) {
    return Stream.of(signatures).map(s -> new TeacherComponent.SignatureDto(s, Level.ALL_LEVELS)).collect(toSet());
  }
}
