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

    String token = createTokenWith("someId");
    assertThat(token).isNotNull();
    assertThat(token).isNotEmpty();
    assertThat(token).isNotBlank();

    String claim = tokenExtractor.extract("unknown-claim", token);
    assertThat(claim).isNull();
  }

  @Test
  public void shouldReturnTokenSubject() {
    String studentId = "student-id";
    String token = createTokenWith(studentId);
    String id = tokenExtractor.getUserId(token);

    assertThat(id).isEqualTo(studentId);
  }

  private String createTokenWith(final String id) {
    return tokenFactory.create(id);
  }
}