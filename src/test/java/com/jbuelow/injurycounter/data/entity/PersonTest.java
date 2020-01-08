package com.jbuelow.injurycounter.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

  private Person p1;
  private Person p2;

  @BeforeEach
  public void setup() {
    p1 = new Person();
    p1.setId(6346L);
    p1.setName("Person One");
    p2 = new Person();
    p2.setId(6348L);
    p2.setName("Person Two");
  }

  @Test
  void setName() {
    p1.setName("Persons Name");
    assertEquals("Persons N.", p1.getShortName());
  }

  @Test
  void compareTo() {
    int diff1 = p1.compareTo(p2);
    assertEquals(-5, diff1);
  }

  @Test
  void sameAs() {
    boolean r1 = p1.sameAs(p1);
    assertTrue(r1);
    boolean r2 = p1.sameAs(p2);
    assertFalse(r2);
    p2.setId(p1.getId());
    boolean r3 = p1.sameAs(p1);
    assertTrue(r3);
    boolean r4 = p1.sameAs(p2);
    assertTrue(r4);
  }

  @Test
  void testEquals() {
    p2.setId(p1.getId());
    boolean r1 = p1.equals(p1);
    assertTrue(r1);
    boolean r2 = p1.equals(p2);
    assertFalse(r2);
  }
}