package org.tommy.mongofirstdemo.component.token;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tommy.mongofirstdemo.component.shared.UserType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTest {

  @Autowired
  private TokenExtractor tokenExtractor;

  @Autowired
  private TokenFactory tokenFactory;

  @Test
  public void shouldReturnUserType_inTokenClaims() {

    String token = createTokenWith("someId", UserType.TEACHER);
    assertThat(token).isNotNull();
    assertThat(token).isNotEmpty();
    assertThat(token).isNotBlank();

    String claim = tokenExtractor.extract("userType", token);
    assertThat(claim).isEqualTo(UserType.TEACHER.name());
  }

  @Test
  public void shouldReturnTokenSubject() {
    String studentId = "student-id";
    String token = createTokenWith(studentId, UserType.STUDENT);
    String id = tokenExtractor.getUserId(token);

    assertThat(id).isEqualTo(studentId);
  }

  private String createTokenWith(final String id, final UserType userType) {
    return tokenFactory.create(id, userType);
  }
}