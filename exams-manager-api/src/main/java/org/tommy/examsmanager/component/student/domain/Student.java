package org.tommy.examsmanager.component.student.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tommy.examsmanager.component.exam.domain.Exam;

@Document(collection = "student")
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

  private List<Exam> exams = new ArrayList<>();

  /**
   * For default, the visibility for the others is false.
   */
  private boolean profileVisible;

  public Student(final String firstName, final String lastName, final String password, final String email) {
    Validate.notNull(email, "email cannot be null");
    this.firstName = toUpperCaseTheFirstLetter(firstName);
    this.lastName = toUpperCaseTheFirstLetter(lastName);
    this.password = password;
    this.email = email;
  }

  public void addExam(final Exam exam) {
    this.exams.add(exam);
  }

  public void addExam(final List<Exam> exams) {
    this.exams.addAll(exams);
  }

  private String toUpperCaseTheFirstLetter(String s) {
    String low = s.toLowerCase();
    char fistLetter = low.charAt(0);
    String rest = low.substring(1);
    String upperCaseFirstLetter = Character.toString(fistLetter).toUpperCase();
    return upperCaseFirstLetter.concat(rest);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("email", email)
        .append("exams", exams)
        .toString();
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