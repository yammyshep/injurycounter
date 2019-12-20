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

}
