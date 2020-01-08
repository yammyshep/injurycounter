package com.jbuelow.injurycounter.data.entity;

import java.io.Serializable;
import java.util.Objects;
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

  /**
   * Checks if this Color instance and the one provided are equal
   *
   * @param c Color object to check for equality against
   * @return equality of this instance and provided
   */
  public boolean equals(Color c) {
    if (Objects.isNull(c)) {
      return false;
    }
    return
        this.r == c.r &&
        this.g == c.g &&
        this.b == c.b;
  }

}
