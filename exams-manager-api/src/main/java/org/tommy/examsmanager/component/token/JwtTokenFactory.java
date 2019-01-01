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

  @Override
  public String create(final String id) {
    return createJwt(id);
  }

  private String createJwt(final String id) {
    return Jwts
        .builder()
        .setSubject(id)
        .setExpiration(getDateAfter(days))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  private static Date getDateAfter(final int days) {
    return from(LocalDate.now().plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
