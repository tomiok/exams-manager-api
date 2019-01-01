package org.tommy.examsmanager.component.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

  private final String secretKey = "some-awesome-key";

  private final int days = 99;

  @Bean
  public TokenFactory jwtTokenFactory() {
    return new JwtTokenFactory(secretKey, days);
  }

  @Bean
  public TokenExtractor tokenExtractor() {
    return new JwtExtractor(secretKey);
  }
}
