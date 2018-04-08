package org.tommy.mongofirstdemo.component.token;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.joda.time.DateTime;
import org.tommy.mongofirstdemo.component.shared.UserType;

public class JwtTokenFactory implements TokenFactory {

  @Override
  public String create(final String id, final UserType userType) {
    return createJwt(id, userType);
  }

  private static String createJwt(final String id, final UserType userType) {
    return Jwts
        .builder()
        .setSubject(id)
        .claim("userType", userType)
        .setExpiration(getDateAfter(7))
        .compact();
  }

  private static Date getDateAfter(final int days) {
    return new DateTime().plusDays(days).toDate();
  }
}
