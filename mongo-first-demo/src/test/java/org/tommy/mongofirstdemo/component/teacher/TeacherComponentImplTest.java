package org.tommy.mongofirstdemo.component.teacher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.tommy.mongofirstdemo.component.teacher.TeacherMother.createComment;
import static org.tommy.mongofirstdemo.component.teacher.TeacherMother.createRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.domain.techer.Teacher;
import org.tommy.mongofirstdemo.domain.techer.TeacherRepository;

@RunWith(SpringRunner.class)
public class TeacherComponentImplTest {

  @MockBean
  private TeacherRepository teacherRepository;

  private TeacherEntityGateway gateway;

  private TeacherComponent component;

  @Before
  public void setUp() {
    gateway = new TeacherEntityGateway(teacherRepository);
    component = new TeacherComponentImpl(gateway);
  }

  @Test
  public void createTeacher() {
    TeacherComponent.TeacherRequest request =
        createRequest(createComment("Tommy have been teaching maths over 10 years"), "Tommy", 1.0, 34.1);

    when(teacherRepository.save(any(Teacher.class))).thenReturn(Teacher.from(request));
    TeacherComponent.TeacherResponse response = component.createTeacher(request);

    assertThat(response).isNotNull();
    assertThat(response.getCity()).isEqualTo("Rosario");
    assertThat(response.getComment().getComment()).contains("teaching maths over 10 years");
  }
}