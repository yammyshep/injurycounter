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

  /**
   * Compare this Person instance against another by alphabetical order
   *
   * @param o Person object to compare against
   * @return alphabetical offset between compared Persons
   */
  @Override
  public int compareTo(Person o) {
    return this.getName().compareTo(o.getName());
  }

  /**
   * Checks if this Person instance and the one provided are the same Person
   * DOES NOT check fields. use equals() for checking if fields are equal
   *
   * @param p Person object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean sameAs(Person p) {
    return Objects.equals(this.getId(), p.getId());
  }

  /**
   * Checks if this Person instance and the one provided are equal on all fields
   *
   * @param p Person object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean equals(Person p) {
    return
        (Objects.equals(this.getId(), p.getId())) &&
            (Objects.equals(this.getName(), p.getName())) &&
            (Objects.equals(this.getShortName(), p.getShortName())) &&
            (Objects.equals(this.getGender(), p.getGender())) &&
            (Objects.equals(this.getGrade(), p.getGrade())) &&
            (Objects.equals(this.getDob(), p.getDob())) &&
            (Objects.nonNull(this.getTeam()) ? this.getTeam().equals(p.getTeam()) : Objects.isNull(p.getTeam()));
  }

}
