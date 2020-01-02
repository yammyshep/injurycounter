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

  public static boolean isEqual(Team t1, Team t2) {
    return
        (Objects.equals(t1.getId(), t2.getId())) &&
            (Objects.equals(t1.getName(), t2.getName())) &&
            (Objects.equals(t1.getAbbreviation(), t2.getAbbreviation())) &&
            (Color.isEqual(t1.getColor(), t2.getColor())) &&
            (Objects.equals(t1.getDescription(), t2.getDescription()));
  }

}
