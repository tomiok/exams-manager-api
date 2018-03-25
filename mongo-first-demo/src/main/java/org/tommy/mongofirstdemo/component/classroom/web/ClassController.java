package org.tommy.mongofirstdemo.component.classroom.web;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.mongofirstdemo.component.classroom.domain.ClassRequest;
import org.tommy.mongofirstdemo.component.classroom.usecase.RequestClassService;
import org.tommy.mongofirstdemo.shared.WebUtils;

@RestController
@RequestMapping("/class-request")
public class ClassController {

  private final RequestClassService requestClassService;

  public ClassController(final RequestClassService requestClassService) {
    this.requestClassService = requestClassService;
  }

  @PostMapping
  public ResponseEntity<Void> createClassRequest(@RequestBody final RequestClassService.Request classRequest,
                                              final HttpServletRequest httpRequest) throws Exception {
    ClassRequest classReq = requestClassService.requestClass(classRequest);
    URI uri = WebUtils.getCreatedEntityUri(classReq.getId(), httpRequest);

    return ResponseEntity.created(uri).build();
  }
}
