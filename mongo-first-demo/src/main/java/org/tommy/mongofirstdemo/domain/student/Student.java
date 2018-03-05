package org.tommy.mongofirstdemo.domain.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@NoArgsConstructor
@Getter
@Setter
public class Student {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  private String password;

  @Indexed(unique = true)
  private String email;

  public Student(final String firstName, final String lastName, final String password, final String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
  }

}
/*
import com.mongodb.client.model.Filters;

filtering the query;
 Bson filter = Filters.and(Filters.eq("x", 0), Filters.gt("y", 0));

 Projections
 Bson projection = new Document("x",0).append("_id",0); we are not going to see _id and x from the json response
                        with 1 will include the field

  Bson projection2 = Projections.fields(Projections.exclude(...).include(....).excludeId());

  Bson sort = Sorts.orderBy(Sorts.ascending(..).descending(..));

 get all the docs using filters, projections
 List<Document> docs = collection
 .find(filter)
 .projection(projection)
 .skip(20) // skip the first 20 elements
 .limit(50) // max 50 elements
 .into(new ArrayList<Document>());
 */