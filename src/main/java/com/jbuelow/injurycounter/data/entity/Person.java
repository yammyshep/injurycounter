package com.jbuelow.injurycounter.data.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "people")
public class Person {

  @Id
  private Long id;

  @NotNull
  private String name;

  private String fullName;

  private String gender;

  @NotNull
  private Integer grade;

  @NotNull
  private Boolean staff = false;

  private Timestamp dob;

}
