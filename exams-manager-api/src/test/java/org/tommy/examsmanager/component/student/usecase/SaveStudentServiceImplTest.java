package org.tommy.examsmanager.component.student.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.examsmanager.component.student.domain.StudentEntityGateway;

@RunWith(MockitoJUnitRunner.class)
public class SaveStudentServiceImplTest {

  @Mock
  private StudentEntityGateway studentEntityGateway;

  @InjectMocks
  private SaveStudentServiceImpl saveStudentService;

  @Test
  public void registerStudent() {
  }
}