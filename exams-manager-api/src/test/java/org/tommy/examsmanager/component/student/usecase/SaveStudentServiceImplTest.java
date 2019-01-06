package org.tommy.examsmanager.component.student.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.examsmanager.component.student.domain.Student;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

@RunWith(MockitoJUnitRunner.class)
public class SaveStudentServiceImplTest {

  @Mock
  private StudentEntityGateway studentEntityGateway;

  @InjectMocks
  private SaveStudentServiceImpl saveStudentService;

  @Test
  public void registerStudent() {
    SaveStudentService.CreateStudentRequest request
        = createReq(
        "alan@apple.com",
        "aLaN",
        "tuRinG",
        "Heav1Pa55");

    Student alan = SaveStudentServiceImpl.createStudent(request);
    when(studentEntityGateway.saveStudent(any(Student.class))).thenReturn(alan);

    Student actual = saveStudentService.registerStudent(request);

    System.out.println(actual);
    assertThat(actual.getEmail()).isEqualTo("alan@apple.com");
    assertThat(actual.getFirstName()).isEqualTo("Alan");
    assertThat(actual.getLastName()).isEqualTo("Turing");
  }

  @Test(expected = NullPointerException.class)
  public void shouldFailGivenEmptyMail() {
    SaveStudentService.CreateStudentRequest request
        = createReq(
        null,
        "arnold",
        "Schwarzer",
        "Heav1pass56");
    saveStudentService.registerStudent(request);
  }

  private SaveStudentService.CreateStudentRequest createReq(
      String email,
      String firstName,
      String lastName,
      String password
  ) {
    SaveStudentService.CreateStudentRequest req = new SaveStudentService.CreateStudentRequest();
    req.setEmail(email);
    req.setFirstName(firstName);
    req.setLastName(lastName);
    req.setPassword(password);

    return req;
  }
}