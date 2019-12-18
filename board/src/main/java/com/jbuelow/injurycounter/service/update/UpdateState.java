package com.jbuelow.injurycounter.service.update;

import lombok.Getter;

public enum UpdateState {

  INPROGRESS(1),
  READY(0),
  NOWRITE(-1),
  DISABLED(-2);

  @Getter
  private final int level;

  UpdateState(int level) {
    this.level = level;
  }
}
