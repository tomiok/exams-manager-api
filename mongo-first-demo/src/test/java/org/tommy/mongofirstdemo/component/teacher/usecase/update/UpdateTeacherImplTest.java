package org.tommy.mongofirstdemo.component.teacher.usecase.update;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;
import org.tommy.mongofirstdemo.component.teacher.TeacherEntityGateway;
import org.tommy.mongofirstdemo.component.teacher.TeacherMother;
import org.tommy.mongofirstdemo.domain.Address;
import org.tommy.mongofirstdemo.domain.teacher.Comment;
import org.tommy.mongofirstdemo.domain.teacher.Level;
import org.tommy.mongofirstdemo.domain.teacher.Signature;
import org.tommy.mongofirstdemo.domain.teacher.Teacher;

@RunWith(SpringRunner.class)
public class UpdateTeacherImplTest {

  private Update updateTeacher;

  @MockBean
  private TeacherEntityGateway gateway;

  @Before
  public void setUp() {
    updateTeacher = new UpdateTeacherImpl(gateway);
  }

  @Test
  public void updatePersonalInformation() {
    Teacher robert = getRobert();
    String comment = "Hi, i am Robbie";
    when(gateway.findTeacherById(anyString())).thenReturn((robert));
    updateTeacher.updatePersonalInformation(createPersonalInfoRequest("Robbie", "Sins", "j@b.com",
        comment));

    assertThat(robert.getFirstName()).isEqualTo("Robbie");
    assertThat(robert.getLastName()).isEqualTo("Sins");
    assertThat(robert.getMail()).isEqualTo("j@b.com");
    assertThat(robert.getComment().getDescription()).isEqualTo(comment);

  }

  @Test
  public void updateSignatures() {
    Teacher robert = getRobert();
    when(gateway.findTeacherById(anyString())).thenReturn((robert));
    updateTeacher.updateSignatures(createSignaturesRequest(getSignatures("Physics")));

    assertThat(robert.getSignatures()).hasSize(1);
    assertThat(robert.getSignatures()).contains(new Signature("Physics", Level.ALL_LEVELS));
  }

  @Test
  public void updateLocation() {
    Teacher robert = getRobert();
    when(gateway.findTeacherById(anyString())).thenReturn((robert));
    updateTeacher.updateLocation(createLocationRequest("Springfield", 2d, 3d, "999", "EverBlack"));

    assertThat(robert.getAddress().getCity()).isEqualTo("Springfield");
    assertThat(robert.getAddress().getLat()).isEqualTo(2d);
    assertThat(robert.getAddress().getLon()).isEqualTo(3d);
    assertThat(robert.getAddress().getStreet()).isEqualTo("EverBlack");
    assertThat(robert.getAddress().getNumber()).isEqualTo("999");
  }

  private static Teacher getTeacher(final String firstName, final String lastName, final Address address,
                                    final Set<Signature> signatures, final Comment comment) {
    return TeacherMother.createTeacher(firstName, lastName, address, signatures, comment);
  }

  private static Teacher getRobert() {
    return getTeacher("Robert", "Rock",
        new Address(1d, 2d, "Evergreen", "123", "Springfield"), getSignatures("Math", "English"),
        new Comment("Robert rock the best teacher"));
  }

  private static Set<Signature> getSignatures(final String... signatures) {
    return Stream.of(signatures).map(s -> new Signature(s, Level.ALL_LEVELS)).collect(toSet());
  }

  private static Update.PersonalInfoRequest createPersonalInfoRequest(final String firstName, final String lastName,
                                                                      final String email, final String comment) {
    return new Update.PersonalInfoRequest("id", firstName, lastName, email, comment, true);
  }

  private static Update.SignaturesRequest createSignaturesRequest(final Set<Signature> signatures) {
    Update.SignaturesRequest request = new Update.SignaturesRequest();
    request.setSignatures(signatures
        .stream()
        .map(s -> new TeacherComponent.SignatureDto(s.getSignature(), s.getLevel()))
        .collect(toSet()));
    request.setTeacherId("id");
    return request;
  }

  private static Update.LocationRequest createLocationRequest(final String city, final double lat, final double lon,
                                                              final String number, final String street) {
    Update.LocationRequest request = new Update.LocationRequest();
    request.setCity(city);
    request.setLat(lat);
    request.setLon(lon);
    request.setNumber(number);
    request.setStreet(street);
    request.setTeacherId("id");

    return request;
  }
}
