package org.tommy.mongofirstdemo.component.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

  @Bean
  public TokenFactory jwtTokenFactory() {
    return new JwtTokenFactory();
  }
}
