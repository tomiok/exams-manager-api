package org.tommy.mongofirstdemo.web;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;

final class WebUtils {

  static URI getCreatedEntityUri(final String entityId, final HttpServletRequest req) throws Exception {
    String selfUrl = extractSelfUrl(req);
    return new URI(selfUrl + "/" + entityId);
  }

  private static String extractSelfUrl(final HttpServletRequest req) {
    return req.getRequestURL().toString();
  }
}
