package org.tommy.mongofirstdemo.component.teacher.web;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.mongofirstdemo.component.teacher.TeacherComponent;
import org.tommy.mongofirstdemo.component.teacher.usecase.update.Update;
import org.tommy.mongofirstdemo.shared.WebUtils;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

  private final TeacherComponent teacherComponent;

  public TeacherController(final TeacherComponent teacherComponent) {
    this.teacherComponent = teacherComponent;
  }

  @PostMapping
  public ResponseEntity<?> createTeacher(@RequestBody final TeacherComponent.TeacherRequest teacherReq,
                                         final HttpServletRequest request) throws Exception {

    TeacherComponent.TeacherResponse teacherResponse = teacherComponent.createTeacher(teacherReq);
    URI uri = WebUtils.getCreatedEntityUri(teacherResponse.getTeacherId(), request);

    return created(uri).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherComponent.TeacherResponse> teacherById(@PathVariable final String id) {
    return ok(teacherComponent.findById(id));
  }

  @PatchMapping("/personal")
  public ResponseEntity<?> updatePersonalInformation(@RequestBody final Update.PersonalInfoRequest req) {
    teacherComponent.updatePersonalInfo(req);
    return ok().build();
  }

  @PatchMapping("/signatures")
  public ResponseEntity<?> updateSignatures(@RequestBody final Update.SignaturesRequest req) {
    teacherComponent.updateSignatures(req);
    return ok().build();
  }

  @PatchMapping("/location")
  public ResponseEntity<?> updateLocation(@RequestBody final Update.LocationRequest req) {
    teacherComponent.updateLocation(req);
    return ok().build();
  }
}
