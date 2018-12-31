package org.tommy.mongofirstdemo.component.token;

import org.tommy.mongofirstdemo.component.shared.UserType;

public interface TokenFactory {

  String create(final String id, final UserType userType);
}
