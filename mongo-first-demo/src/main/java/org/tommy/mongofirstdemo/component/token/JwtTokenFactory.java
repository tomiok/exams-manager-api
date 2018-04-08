package org.tommy.mongofirstdemo.component.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.joda.time.DateTime;
import org.tommy.mongofirstdemo.component.shared.UserType;

public class JwtTokenFactory implements TokenFactory {

  private final String secretKey;

  private final int days;

  JwtTokenFactory(final String secretKey, final int days) {
    this.secretKey = secretKey;
    this.days = days;
  }

  @Override
  public String create(final String id, final UserType userType) {
    return createJwt(id, userType);
  }

  private String createJwt(final String id, final UserType userType) {
    return Jwts
        .builder()
        .setSubject(id)
        .claim("userType", userType)
        .setExpiration(getDateAfter(days))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  private static Date getDateAfter(final int days) {
    return new DateTime().plusDays(days).toDate();
  }
}
