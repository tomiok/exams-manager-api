package org.tommy.mongofirstdemo.component.token;

public interface TokenExtractor {

  String extract(final String claim, final String token);

  String getUserId(final String token);
}
