package org.tommy.mongofirstdemo.component.classroom.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassStatus;
import org.tommy.mongofirstdemo.component.studend.StudentMother;
import org.tommy.mongofirstdemo.component.student.domain.Student;
import org.tommy.mongofirstdemo.component.student.domain.StudentRepository;
import org.tommy.mongofirstdemo.component.teacher.TeacherMother;
import org.tommy.mongofirstdemo.component.teacher.domain.Teacher;
import org.tommy.mongofirstdemo.component.teacher.domain.TeacherRepository;

@RunWith(SpringRunner.class)
public class RequestClassServiceImplTest {

  @MockBean
  private StudentRepository studentRepository;

  @MockBean
  private TeacherRepository teacherRepository;

  @MockBean
  private ClassRepository classRepository;

  private RequestClassService requestClassService;

  @Before
  public void setUp() {
    requestClassService = new RequestClassServiceImpl(studentRepository, teacherRepository, classRepository);
  }

  @Test
  public void requestClass() {
    Student alan = StudentMother.createAlan();
    Teacher albert = TeacherMother.createAlbertEinstein();
    String comment = "Ey buddy help me with maths";

    when(studentRepository.findById(anyString())).thenReturn(Optional.of(alan));
    when(teacherRepository.findById(anyString())).thenReturn(Optional.of(albert));
    when(classRepository.save(any(ClassRequest.class))).thenReturn(create(alan, albert, comment));
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

  private ClassRequest create(final Student student, final Teacher teacher, final String comment) {
    return new ClassRequest(student, teacher, comment, ClassStatus.PENDING, Instant.now());
  }
}