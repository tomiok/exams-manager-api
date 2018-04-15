package org.tommy.mongofirstdemo.component.token;

import io.jsonwebtoken.Jwts;

public class JwtExtractor implements TokenExtractor {

  private final String secretKey;

  JwtExtractor(final String secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public String extract(final String claim, final String token) {
    return Jwts
        .parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody().get(claim, String.class);
  }

  @Override
  public String getUserId(final String token) {
    return Jwts
        .parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
