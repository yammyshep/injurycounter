package com.jbuelow.injurycounter.data.entity;

import java.sql.Timestamp;
import java.time.Instant;
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

  @Override
  public int compareTo(Injury o) {
    return this.getTimestamp().compareTo(o.getTimestamp());
  }

}
