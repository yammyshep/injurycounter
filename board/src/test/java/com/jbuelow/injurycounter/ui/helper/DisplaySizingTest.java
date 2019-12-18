package com.jbuelow.injurycounter.ui.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Dimension;
import org.junit.jupiter.api.Test;

class DisplaySizingTest {

  @Test
  void displayPercent() {
    DisplaySizing s = new DisplaySizing();
    s.setDisplayDimension(new Dimension(1280,720));

    int percent100resultfrom720 = s.displayPercent(100f);
    assertEquals(720, percent100resultfrom720);

    int percent50from720 = s.displayPercent(50f);
    assertEquals(360, percent50from720);

    s = new DisplaySizing();
    s.setDisplayDimension(new Dimension(1920, 1080));

    int percent100from1080 = s.displayPercent(100f);
    assertEquals(1080, percent100from1080);

    int percent50from1080 = s.displayPercent(50f);
    assertEquals(540, percent50from1080);
  }
}