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

  /**
   * Compare this Injury instance against another by timestamp
   *
   * @param o Injury object to compare against
   * @return time offset between compared injuries
   */
  @Override
  public int compareTo(Injury o) {
    return this.getTimestamp().compareTo(o.getTimestamp());
  }

  /**
   * Checks if this Injury instance and the one provided are the same Injury
   * DOES NOT check fields. use equals() for checking if fields are equal
   *
   * @param i Injury object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean sameAs(Injury i) {
    if (Objects.isNull(i)) {
      return false;
    }
    return Objects.equals(this.getId(), i.getId());
  }

  /**
   * Checks if this Injury instance and the one provided are equal on all fields
   *
   * @param i Injury object to check for deep equality against
   * @return equality of this instance and provided
   */
  public boolean equals(Injury i) {
    if (Objects.isNull(i)) {
      return false;
    }
    return
        (Objects.equals(this.getId(), i.getId())) &&
            (this.isHidden() == i.isHidden()) &&
            (this.isReviewed() == i.isReviewed()) &&
            (this.isHideFromStats() == i.isHideFromStats()) &&
            (this.getPerson().equals(i.getPerson())) &&
            (this.getInstigator().equals(i.getInstigator())) &&
            (Objects.equals(this.getTimestamp(), i.getTimestamp())) &&
            (Objects.equals(this.getDescription(), i.getDescription())) &&
            (this.isHideDescription() == i.isHideDescription()) &&
            (Arrays.equals(this.getDrawing(), i.getDrawing())) &&
            (this.isHideDrawing() == i.isHideDrawing());
  }

}
