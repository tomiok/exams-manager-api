package org.tommy.examsmanager.component.exam.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.Test;

public class ExamTest {

  private LocalDate now = LocalDate.now();

  @Test(expected = IllegalArgumentException.class)
  public void shouldFail_GivenBlankSignature() {

    new Exam("", now.plusDays(4), true);
  }

  @Test(expected = NullPointerException.class)
  public void shouldFail_GivenNullSignature() {
    new Exam(null, now.plusDays(2), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFail_GivenSameDay() {
    new Exam("Maths", now, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFail_GivenEmptyComments() {
    new Exam("Maths", now.plusDays(5), "");
  }

  @Test
  public void shouldCreateExamWithNullComments() {
    Exam actual = new Exam("Maths", now.plusDays(5), null);

    assertThat(actual.getComments()).isNull();
    assertThat(actual.getDate()).isEqualTo(now.plusDays(5));
    assertThat(actual.getSignature()).isEqualTo("Maths");
  }




}