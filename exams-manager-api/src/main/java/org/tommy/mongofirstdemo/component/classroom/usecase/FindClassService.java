package org.tommy.mongofirstdemo.component.classroom.usecase;

import java.util.Set;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.shared.UserType;

public interface FindClassService {

  Set<ClassRequest> findClass(final String id, final UserType userType);
}
