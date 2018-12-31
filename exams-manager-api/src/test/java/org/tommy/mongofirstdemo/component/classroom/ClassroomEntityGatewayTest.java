package org.tommy.mongofirstdemo.component.classroom;

import static org.mockito.ArgumentMatchers.any;
import static org.tommy.mongofirstdemo.component.classroom.ClassMother.createStandardClass;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRepository;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.domain.DuplicatedClassException;

@RunWith(SpringRunner.class)
public class ClassroomEntityGatewayTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @MockBean
  private ClassRepository classRepository;

  private ClassroomEntityGateway gateway;

  @Before
  public void setUp() {
    gateway = new ClassroomEntityGateway(classRepository);
  }

  @Test
  public void shouldFailWithNearbyClassSaved() {
    thrown.expect(DuplicatedClassException.class);
    thrown.expectMessage("This class is duplicated, please cancel the old one or wait until");
    ClassRequest savedClass = createStandardClass();
    Mockito.when(classRepository.findByStudentIdAndTeacherId(any(ObjectId.class), any(ObjectId.class)))
        .thenReturn(savedClass);

    gateway.save(savedClass);
  }
}
