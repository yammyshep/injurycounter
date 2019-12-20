package com.jbuelow.injurycounter.ui.helper;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import lombok.Getter;
import lombok.Setter;

public class DisplaySizing {

  @Setter
  @Getter
  private Dimension displayDimension;

  public int displayPercent(float percent) {
    assert displayDimension != null;

    int h = displayDimension.height;
    float p = percent / 100;
    return (int) (h*p);
  }

  public int displayPercentOfWidth(float percent) {
    assert displayDimension != null;

    int w = displayDimension.width;
    float p = percent / 100;
    return (int) (w*p);
  }

  private static Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
  }

}
