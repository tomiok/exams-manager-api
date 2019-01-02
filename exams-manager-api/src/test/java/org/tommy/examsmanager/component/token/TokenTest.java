package org.tommy.examsmanager.component.token;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTest {

  @Autowired
  private TokenExtractor tokenExtractor;

  @Autowired
  private TokenFactory tokenFactory;

  @Test
  public void shouldReturnUserType_inTokenClaims() {

    String email = "tomi@msn.com";
    String token = createTokenWith("someId", email);
    assertThat(token).isNotNull();
    assertThat(token).isNotEmpty();
    assertThat(token).isNotBlank();

    String claim = tokenExtractor.extract("email", token);
    assertThat(claim).isEqualTo(email);
  }

  @Test
  public void shouldReturnTokenSubject() {
    String studentId = "student-id";
    String email = "tomi@msn.com";
    String token = createTokenWith(studentId, email);
    String id = tokenExtractor.getStudentId(token);

    assertThat(id).isEqualTo(studentId);
  }

  private String createTokenWith(final String id, String email) {
    return tokenFactory.create(id, email);
  }
}