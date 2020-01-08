package com.jbuelow.injurycounter.data.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "injuries")
public class Injury implements Comparable<Injury> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private boolean hidden = false;

  @NotNull
  private boolean reviewed = false;

  @NotNull
  private boolean hideFromStats = false;

  @NotNull
  @OneToOne
  private Person person;

  @OneToOne
  private Person instigator;

  @NotNull
  private Timestamp timestamp = Timestamp.from(Instant.now());

  private String description;

  @NotNull
  private boolean hideDescription = false;

  private byte[] drawing;

  @NotNull
  private boolean hideDrawing = false;

  @Override
  public int compareTo(Injury o) {
    return this.getTimestamp().compareTo(o.getTimestamp());
  }

  public static boolean isEqual(Injury i1, Injury i2) {
    return
        (Objects.equals(i1.getId(), i2.getId())) &&
        (i1.isHidden() == i2.isHidden()) &&
        (i1.isReviewed() == i2.isReviewed()) &&
        (i1.isHideFromStats() == i2.isHideFromStats()) &&
        (Person.isEqual(i1.getPerson(), i2.getPerson())) &&
        (Person.isEqual(i1.getInstigator(), i2.getInstigator())) &&
        (Objects.equals(i1.getTimestamp(), i2.getTimestamp())) &&
        (Objects.equals(i1.getDescription(), i2.getDescription())) &&
        (i1.isHideDescription() == i2.isHideDescription()) &&
        (Arrays.equals(i1.getDrawing(), i2.getDrawing())) &&
        (i1.isHideDrawing() == i2.isHideDrawing());
  }

}
