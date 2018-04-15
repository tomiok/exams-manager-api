package org.tommy.mongofirstdemo.component.classroom.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.tommy.mongofirstdemo.component.classroom.ClassMother.create;

import java.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.component.classroom.ClassMother;
import org.tommy.mongofirstdemo.component.classroom.ClassroomEntityGateway;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;
import org.tommy.mongofirstdemo.component.student.StudentEntityGateway;
import org.tommy.mongofirstdemo.component.student.StudentMother;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.teacher.TeacherEntityGateway;
import org.tommy.mongofirstdemo.component.teacher.TeacherMother;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;

@RunWith(SpringRunner.class)
public class RequestClassServiceImplTest {

  @MockBean
  private StudentEntityGateway studentEntityGateway;

  @MockBean
  private TeacherEntityGateway teacherEntityGateway;

  @MockBean
  private ClassroomEntityGateway classroomEntityGateway;

  private RequestClassService requestClassService;

  @Before
  public void setUp() {
    requestClassService =
        new RequestClassServiceImpl(studentEntityGateway, teacherEntityGateway, classroomEntityGateway);
  }

  @Test
  public void requestClass() {
    Student alan = StudentMother.createAlan();
    Teacher albert = TeacherMother.createAlbertEinstein();
    String comment = "Ey buddy help me with maths";

    when(studentEntityGateway.findById(anyString())).thenReturn(alan);
    when(teacherEntityGateway.findTeacherById(anyString())).thenReturn(albert);
    when(classroomEntityGateway.save(any(ClassRequest.class))).thenReturn(create(alan, albert, comment));
    ClassRequest cr = requestClassService.requestClass(createRequest(comment));

    assertThat(cr.getStudent().getFirstName()).isEqualTo(alan.getFirstName());
    assertThat(cr.getTeacher().getFirstName()).isEqualTo(albert.getFirstName());
    assertThat(cr.getComment()).isEqualTo(comment);
    assertThat(cr.getStatus()).isEqualTo(ClassStatus.PENDING);
  }

  private RequestClassService.Request createRequest(final String comment) {
    RequestClassService.Request r = new RequestClassService.Request();
    r.setComment(comment);
    r.setStudentId("one");
    r.setTeacherId("two");

    return r;
  }


}