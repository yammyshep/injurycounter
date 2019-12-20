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

}
