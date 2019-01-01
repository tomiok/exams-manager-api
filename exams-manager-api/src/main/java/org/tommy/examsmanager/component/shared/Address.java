package org.tommy.examsmanager.component.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Getter
@Setter
public class Address {

  @Id
  private long id;

  private double lat;

  private double lon;

  private String street;

  private String number;

  private String city;

  private Address() {}

  public Address(final double lat, final double lon, final String street, final String number, final String city) {
    this.lat = lat;
    this.lon = lon;
    this.street = street;
    this.number = number;
    this.city = city;
  }

  public static Address from(final double lat, final double lon, final String street, final String city,
                             final String number) {
    return new Address(lat, lon, street, number, city);
  }
}
