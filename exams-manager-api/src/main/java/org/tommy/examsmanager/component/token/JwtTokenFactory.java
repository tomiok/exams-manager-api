package org.tommy.examsmanager.component.token;

import static java.util.Date.from;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JwtTokenFactory implements TokenFactory {

  private final String secretKey;

  private final int days;

  JwtTokenFactory(final String secretKey, final int days) {
    this.secretKey = secretKey;
    this.days = days;
  }

  private static Date getDateAfter(final int days) {
    return from(LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  @Override
  public String create(final String id, final String email) {
    return createJwt(id, email);
  }

  private String createJwt(final String id, String email) {
    return Jwts
        .builder()
        .setSubject(id)
        .claim("email", email)
        .setExpiration(getDateAfter(days))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }
}
