package org.tommy.mongofirstdemo.shared;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;

public final class WebUtils {

  public static URI getCreatedEntityUri(final String entityId, final HttpServletRequest req) throws Exception {
    String selfUrl = extractSelfUrl(req);
    return new URI(selfUrl + "/" + entityId);
  }

  private static String extractSelfUrl(final HttpServletRequest req) {
    return req.getRequestURL().toString();
  }

  public static String getAuthorizationToken(final HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    String token = bearerToken.substring("Bearer ".length(), bearerToken.length());
    return token.trim();
  }
}
