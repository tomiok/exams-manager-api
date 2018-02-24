package org.tommy.mongofirstdemo.component.teacher;

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
}
