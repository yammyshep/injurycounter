package com.jbuelow.injurycounter.data.entity;

import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
public class AccessLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private Timestamp timestamp = Timestamp.from(Instant.now());

  @NotNull
  @Type(type = "javax.sql.rowset.serial.SerialJavaObject")
  @Column(columnDefinition = "VARCHAR(8192)")
  private ClientInfo request;

  @OneToOne
  private Injury injury;

  @OneToOne
  private Person person;

  public static AccessLog forInjury(Injury injury, HttpServletRequest request) {
    AccessLog al = new AccessLog();
    al.setInjury(injury);
    al.setRequest(new ClientInfo(request));
    return al;
  }

  public static AccessLog forPerson(Person person, HttpServletRequest request) {
    AccessLog al = new AccessLog();
    al.setPerson(person);
    al.setRequest(new ClientInfo(request));
    return al;
  }

}
