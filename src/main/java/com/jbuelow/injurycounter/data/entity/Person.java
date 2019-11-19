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
public class Person implements Comparable<Person> {

  @Id
  private Long id;

  @NotNull
  private String name;

  public void setName(String name) {
    this.name = name;
    this.shortName = name.split(" ", 2)[0]+" "+name.split(" ", 2)[1].substring(0,1)+".";
  }

  private String shortName;

  private String gender;

  private Integer grade;

  @NotNull
  private Boolean staff = false;

  private Timestamp dob;

  @Override
  public int compareTo(Person o) {
    return this.getName().compareTo(o.getName());
  }
}
