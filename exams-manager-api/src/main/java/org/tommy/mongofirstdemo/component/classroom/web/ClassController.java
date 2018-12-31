package org.tommy.mongofirstdemo.component.classroom.web;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.usecase.FindClassService;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassService;
import org.tommy.mongofirstdemo.component.shared.UserType;
import org.tommy.mongofirstdemo.component.token.TokenExtractor;
import org.tommy.mongofirstdemo.shared.WebUtils;

@RestController
@RequestMapping("/class-request")
public class ClassController {

  private final RequestClassService requestClassService;

  private final FindClassService findClassService;

  private final TokenExtractor tokenExtractor;

  ClassController(final RequestClassService requestClassService,
                  final FindClassService findClassService,
                  final TokenExtractor tokenExtractor) {
    this.requestClassService = requestClassService;
    this.findClassService = findClassService;
    this.tokenExtractor = tokenExtractor;
  }

  @PostMapping
  public ResponseEntity<Void> createClassRequest(@RequestBody final RequestClassService.Request classRequest,
                                                 final HttpServletRequest httpRequest) throws Exception {
    ClassRequest classReq = requestClassService.requestClass(classRequest);
    URI uri = WebUtils.getCreatedEntityUri(classReq.getId(), httpRequest);

    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<Set<ClassResponse>> getMyClasses(final HttpServletRequest request) {
    String token = WebUtils.getAuthorizationToken(request);
    UserType userType = UserType.valueOf(tokenExtractor.extract("userType", token));
    String userId = tokenExtractor.getUserId(token);

    Set<ClassResponse> classResponses = findClassService.findClass(userId, userType)
        .stream()
        .map(ClassResponse::from)
        .collect(toSet());

    return ok(classResponses);
  }

}
