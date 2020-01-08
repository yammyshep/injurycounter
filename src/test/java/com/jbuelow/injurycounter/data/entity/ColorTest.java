package com.jbuelow.injurycounter.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColorTest {

  private Color c;

  @BeforeEach
  public void setup() {
    c = new Color();
    c.setR(32);
    c.setG(64);
    c.setB(128);
  }

  @Test
  void constructor() {
    Color col = new Color(255, 200, 100);
    assertEquals(255, col.getR());
    assertEquals(200, col.getG());
    assertEquals(100, col.getB());
  }

  @Test
  void toAwtColor() {
    java.awt.Color col = c.toAwtColor();
    assertEquals(32, col.getRed());
    assertEquals(64, col.getGreen());
    assertEquals(128, col.getBlue());
  }

  @Test
  void toHtmlHexColor() {
    String html = c.toHtmlHexColor();
    assertEquals("#204080", html);
  }

  @Test
  void testEquals() {
    Color target = null;
    boolean r1 = c.equals(target);
    assertFalse(r1);
    target = new Color(23, 67, 85);
    boolean r2 = c.equals(target);
    assertFalse(r2);
    target = new Color(32, 64, 100);
    boolean r3 = c.equals(target);
    assertFalse(r3);
    target = new Color(32, 64, 128);
    boolean r4 = c.equals(target);
    assertTrue(r4);
  }
}