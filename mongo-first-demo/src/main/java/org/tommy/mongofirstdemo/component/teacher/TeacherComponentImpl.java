package org.tommy.mongofirstdemo.component.teacher;

import org.apache.commons.lang3.Validate;
import org.tommy.mongofirstdemo.component.teacher.usecase.update.Update;

public class TeacherComponentImpl implements TeacherComponent {

  private final TeacherEntityGateway entityGateway;

  private final Update updateService;

  TeacherComponentImpl(final TeacherEntityGateway entityGateway,
                       final Update updateService) {
    Validate.notNull(entityGateway, "entityGateway bean cannot be null");
    Validate.notNull(updateService, "updateService cannot be null");

    this.entityGateway = entityGateway;
    this.updateService = updateService;
  }

  @Override
  public TeacherResponse createTeacher(final TeacherRequest request) {
    return entityGateway.transform(request);
  }

  @Override
  public TeacherResponse findById(final String id) {
    return entityGateway.findById(id);
  }

  @Override
  public void updatePersonalInfo(final Update.PersonalInfoRequest request) {
    updateService.updatePersonalInformation(request);
  }

  @Override
  public void updateLocation(final Update.LocationRequest request) {
    updateService.updateLocation(request);
  }

  @Override
  public void updateSignatures(final Update.SignaturesRequest request) {
    updateService.updateSignatures(request);
  }
}
