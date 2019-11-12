package com.jbuelow.injurycounter.data.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {

  @Id
  private Long id;

  @NotNull
  private String name;

  private String fullName;

  private String gender;

  @NotNull
  private Integer grade;

  private Advisory advisory;

  @NotNull
  private Boolean staff;

  private Timestamp dob;

}
