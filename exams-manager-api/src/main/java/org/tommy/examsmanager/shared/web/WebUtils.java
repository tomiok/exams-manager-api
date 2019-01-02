package org.tommy.examsmanager.shared.web;

import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebUtils {

  private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

  public WebUtils() {
    throw new RuntimeException();
  }

  public static URI getCreatedEntityUri(final String entityId, final HttpServletRequest req) {
    String selfUrl = extractSelfUrl(req);
    try {
      return new URI(selfUrl + "/" + entityId);
    } catch (URISyntaxException e) {
      log.warn("cannot create the URI", e);
      return null;
    }
  }

  private static String extractSelfUrl(final HttpServletRequest req) {
    return req.getRequestURL().toString();
  }

  public static String getAuthorizationToken(final HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    String token = bearerToken.substring("Bearer ".length());
    validateToken(token);
    return token.trim();
  }

  private static void validateToken(String token) {
    Validate.notBlank(token);
  }
}
