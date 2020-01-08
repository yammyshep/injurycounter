package com.jbuelow.injurycounter.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {

  private Team t1;
  private Team t2;

  @BeforeEach
  public void setup() {
    t1 = new Team();
    t1.setId(6413L);
    t1.setName("Team One");
    t2 = new Team();
    t2.setId(6412L);
    t2.setName("Team Two");
  }

  @Test
  void setName() {
    t1.setName("First Team");
    assertEquals("F", t1.getAbbreviation());
  }

  @Test
  void toHtmlFlag() {
    String r1 = t1.toHtmlFlag();
    assertEquals("", r1);
    Color c = new Color(255, 0, 0);
    t1.setColor(c);
    String r2 = t1.toHtmlFlag();
    assertEquals("<font color=#FF0000>\u2691</font>", r2);
    c = new Color(128, 128, 128);
    t1.setColor(c);
    String r3 = t1.toHtmlFlag();
    assertEquals("<font color=#808080>\u2691</font>", r3);
  }

  @Test
  void sameAs() {
    boolean r1 = t1.sameAs(t1);
    assertTrue(r1);
    boolean r2 = t1.sameAs(t2);
    assertFalse(r2);
    t2.setId(t1.getId());
    boolean r3 = t1.sameAs(t1);
    assertTrue(r3);
    boolean r4 = t1.sameAs(t2);
    assertTrue(r4);
  }

  @Test
  void testEquals() {
    t2.setId(t1.getId());
    boolean r1 = t1.equals(t1);
    assertTrue(r1);
    boolean r2 = t1.equals(t2);
    assertFalse(r2);
  }
}