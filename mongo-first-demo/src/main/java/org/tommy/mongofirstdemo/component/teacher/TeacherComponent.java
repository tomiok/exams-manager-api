package org.tommy.mongofirstdemo.component.teacher;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommy.mongofirstdemo.component.teacher.usecase.Update;
import org.tommy.mongofirstdemo.domain.teacher.Level;
import org.tommy.mongofirstdemo.domain.teacher.Signature;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;

public interface TeacherComponent {

  TeacherResponse createTeacher(final TeacherRequest request);

  TeacherResponse findById(final String id);

  void updatePersonalInfo(final Update.PersonalInfoRequest request);

  void updateLocation(final Update.LocationRequest request);

  void updateSignatures(final Update.SignaturesRequest request);

  @Getter
  @Setter
  @NoArgsConstructor
  class TeacherResponse {

    private String teacherId;

    private String firstName;

    private String lastName;

    private String mail;

    private Comment comment;

    private String city;

    private String street;

    private String number;

    private Double lat;

    private Double lon;

    private Set<SignatureDto> signature;

    private boolean goToStudentHouse;

    TeacherResponse(final Teacher teacher) {
      this.teacherId = teacher.getId();
      this.firstName = teacher.getFirstName();
      this.lastName = teacher.getLastName();
      this.mail = teacher.getMail();
      ofNullable(teacher.getComment()).ifPresent(comment -> this.comment = new Comment(comment.getDescription()));
      ofNullable(teacher.getAddress()).ifPresent(address -> {
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.lat = address.getLat() != 0d ? address.getLat() : null;
        this.lon = address.getLon() != 0d ? address.getLon() : null;
      });
      this.signature = teacher.getSignatures().stream().map(SignatureDto::fromModel).collect(toSet());
      this.goToStudentHouse = teacher.isGoToStudentHouse();
    }

  }

  @Getter
  @Setter
  class TeacherRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Comment comment;

    private double lat;

    private double lon;

    private String street;

    private String number;

    private String city;

    private Set<SignatureDto> signatures;

    private boolean goToStudentHouse;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class Comment {

    private String comment;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  class SignatureDto {

    private String signature;

    private Level level;

    static SignatureDto fromModel(final Signature signature) {
      return new SignatureDto(signature.getSignature(), signature.getLevel());
    }
  }
}
