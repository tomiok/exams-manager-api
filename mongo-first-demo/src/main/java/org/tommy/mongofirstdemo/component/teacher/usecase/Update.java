package org.tommy.mongofirstdemo.component.teacher.usecase;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;

public interface Update {

  void updatePersonalInformation(final PersonalInfoRequest request);

  void updateSignatures(final SignaturesRequest request);

  void updateLocation(final LocationRequest request);

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Setter
  class PersonalInfoRequest {

    private String teacherId;

    private String firstName;

    private String lastName;

    private String email;

    private String comment;

    private boolean goToStudentHouse;
  }

  @Getter
  @Setter
  class LocationRequest {

    private String teacherId;

    private String city;

    private String street;

    private String number;

    private double lat;

    private double lon;
  }

  @Getter
  @Setter
  class SignaturesRequest {

    private String teacherId;

    private Set<TeacherComponent.SignatureDto> signatures;
  }
}
