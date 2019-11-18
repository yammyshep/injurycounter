package com.jbuelow.injurycounter.ui.helper;

import java.awt.Dimension;
import lombok.Setter;

public class DisplaySizing {

  @Setter
  private Dimension displayDimension;

  public int displayPercent(float percent) {
    assert displayDimension != null;

    int h = displayDimension.height;
    float p = percent / 100;
    return (int) (h*p);
  }

}
