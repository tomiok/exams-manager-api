package org.tommy.examsmanager.shared.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
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
    validator(bearerToken, () -> new IllegalArgumentException("Authorization header is null"));
    String token = bearerToken.substring("Bearer ".length());
    validator(token, () -> new IllegalArgumentException("JWT is null"));
    return token.trim();
  }

  private static void validator(Object value, Supplier<RuntimeException> e) {
    if (value == null) {
      throw e.get();
    }

    if (value.getClass().equals(String.class)) {
      String str = (String) value;
      if (str.isEmpty() || str.trim().isEmpty()) {
        throw e.get();
      }
    }
  }
}
