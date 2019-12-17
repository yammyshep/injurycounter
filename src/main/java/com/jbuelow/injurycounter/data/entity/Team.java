package com.jbuelow.injurycounter.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {

  @Id
  private Long id;

  @NotNull
  private String name;

  private String description;

}
