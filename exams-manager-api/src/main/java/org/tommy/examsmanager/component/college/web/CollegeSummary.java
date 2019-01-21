package org.tommy.examsmanager.component.college.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CollegeSummary {

  private String name;

  private String address;

  CollegeSummary(final String name, final String address) {
    this.name = name;
    this.address = address;
  }

  public CollegeSummary(final String name) {
    this.name = name;
  }
}
