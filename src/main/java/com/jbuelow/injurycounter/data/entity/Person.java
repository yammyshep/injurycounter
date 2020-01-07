package com.jbuelow.injurycounter.data.entity;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

  private Date dob;

  @OneToOne
  private Team team;

  @Override
  public int compareTo(Person o) {
    return this.getName().compareTo(o.getName());
  }

  public static boolean isEqual(Person p1, Person p2) {
    if (p1 == p2) {
      return true;
    }
    return
        (Objects.equals(p1.getId(), p2.getId())) &&
        (Objects.equals(p1.getName(), p2.getName())) &&
        (Objects.equals(p1.getShortName(), p2.getShortName())) &&
        (Objects.equals(p1.getGender(), p2.getGender())) &&
        (Objects.equals(p1.getGrade(), p2.getGrade())) &&
        (Objects.equals(p1.getDob(), p2.getDob())) &&
        (Objects.nonNull(p1.getTeam()) ? Team.isEqual(p1.getTeam(), p2.getTeam()) : Objects.isNull(p2.getTeam()));
  }
}
