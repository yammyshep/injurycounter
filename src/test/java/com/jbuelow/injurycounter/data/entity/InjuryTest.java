package com.jbuelow.injurycounter.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InjuryTest {

  private Injury i1;
  private Injury i2;
  private Person p1;
  private Person p2;

  @BeforeEach
  public void setup() {
    p1 = new Person();
    p1.setId(4756L);
    p1.setName("Person One");
    p2 = new Person();
    p2.setId(3165L);
    p2.setName("Person Two");
    i1 = new Injury();
    i1.setId(4356L);
    i1.setPerson(p1);
    i2 = new Injury();
    i2.setId(5673L);
    i2.setPerson(p2);
    i2.setTimestamp(i1.getTimestamp());
  }

  @Test
  void compareTo() {
    int diff1 = i1.compareTo(i2);
    assertEquals(0, diff1);
    Timestamp newtime = new Timestamp(i2.getTimestamp().getTime()+1);
    i2.setTimestamp(newtime);
    int diff2 = i1.compareTo(i2);
    assertEquals(-1, diff2);
  }

  @Test
  void sameAs() {
    boolean result1 = i1.sameAs(i1);
    assertTrue(result1);
    boolean result2 = i1.sameAs(i2);
    assertFalse(result2);
    i2.setId(i1.getId());
    boolean result3 = i1.sameAs(i1);
    assertTrue(result3);
    boolean result4 = i1.sameAs(i2);
    assertTrue(result4);
  }

  @Test
  void testEquals() {
    i2.setId(i1.getId());
    boolean result1 = i1.equals(i1);
    assertTrue(result1);
    boolean result2 = i1.equals(i2);
    assertFalse(result2);
  }
}