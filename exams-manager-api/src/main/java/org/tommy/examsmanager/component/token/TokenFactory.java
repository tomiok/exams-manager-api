package org.tommy.examsmanager.component.token;

import org.tommy.examsmanager.component.shared.UserType;

public interface TokenFactory {

  String create(final String id, final UserType userType);
}
