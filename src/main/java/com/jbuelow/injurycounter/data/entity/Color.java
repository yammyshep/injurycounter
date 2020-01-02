package com.jbuelow.injurycounter.data.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Color implements Serializable {

  private final static long serialVersionUID = 6489564895351736524L;

  private int r;
  private int g;
  private int b;

  public java.awt.Color toAwtColor() {
    return new java.awt.Color(r, g, b);
  }

  public String toHtmlHexColor() {
    StringBuilder sb = new StringBuilder();
    sb.append("#");
    sb.append(String.format("%02X", r));
    sb.append(String.format("%02X", g));
    sb.append(String.format("%02X", b));
    return sb.toString();
  }

  public static boolean isEqual(Color c1, Color c2) {
    return
        c1.r == c2.r &&
            c1.g == c2.g &&
            c1.b == c2.b;
  }

}
