package org.tommy.examsmanager.component.college.web;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.examsmanager.component.college.domain.College;
import org.tommy.examsmanager.component.college.usecase.FindCollegeService;
import org.tommy.examsmanager.component.college.usecase.SaveCollegeService;
import org.tommy.examsmanager.shared.web.WebUtils;

@RestController
@RequestMapping("/college")
public class CollegeController {

  private final SaveCollegeService saveCollegeService;

  private final FindCollegeService findCollegeService;

  public CollegeController(final SaveCollegeService saveCollegeService,
                           final FindCollegeService findCollegeService) {
    this.saveCollegeService = saveCollegeService;
    this.findCollegeService = findCollegeService;
  }

  @PostMapping
  public ResponseEntity<CollegeSummary> saveCollege(@RequestBody SaveCollegeHttpReq collegeHttpReq,
                                                    HttpServletRequest servletRequest) {
    WebUtils.getAuthorizationToken(servletRequest);
    College c = saveCollegeService.save(collegeHttpReq.getName(), collegeHttpReq.getAddress());

    return ok(new CollegeSummary(c.getName(), c.getAddress()));
  }

  @GetMapping("/{name}")
  public ResponseEntity<List<CollegeSummary>> findCollegeByName(
      @PathVariable("name") String name,
      HttpServletRequest req
  ) {
    WebUtils.getAuthorizationToken(req);
    List<College> colleges = findCollegeService.findCollegesByName(name);
    List<CollegeSummary> response =
        colleges
            .stream()
            .map(college -> new CollegeSummary(college.getName(), college.getAddress()))
            .collect(toList());

    return ok(response);
  }
}
