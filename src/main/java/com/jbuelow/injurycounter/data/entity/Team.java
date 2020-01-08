package com.jbuelow.injurycounter.data.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String name;

  public void setName(String name) {
    if (!Objects.equals(this.name, name)) {
      this.name = name;
      this.abbreviation = name.substring(0,1);
    }
  }

  @NotNull
  private String abbreviation;

  private Color color;

  @OneToOne
  private Person captain;

  private String description;

  public static String toHtmlFlag(Team team) {
    StringBuilder sb = new StringBuilder();
    if (Objects.nonNull(team) && Objects.nonNull(team.getColor())) {
      sb.append("<font color=");
      sb.append(team.getColor().toHtmlHexColor());
      sb.append(">\u2691</font>");
    }
    return sb.toString();
  }

  /**
   * Checks if this Team instance and the one provided are the same Team
   * DOES NOT check fields. use equals() for checking if fields are equal
   *
   * @param t Team object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean sameAs(Team t) {
    if (Objects.isNull(t)) {
      return false;
    }
    return Objects.equals(this.getId(), t.getId());
  }

  /**
   * Checks if this Team instance and the one provided are equal on all fields
   *
   * @param t Team object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean equals(Team t) {
    if (Objects.isNull(t)) {
      return false;
    }
    return
        (Objects.equals(this.getId(), t.getId())) &&
        (Objects.equals(this.getName(), t.getName())) &&
        (Objects.equals(this.getAbbreviation(), t.getAbbreviation())) &&
        (Objects.nonNull(this.getColor()) ? this.getColor().equals(t.getColor()) : Objects.isNull(t.getColor())) &&
        (Objects.equals(this.getDescription(), t.getDescription()));
  }

}
