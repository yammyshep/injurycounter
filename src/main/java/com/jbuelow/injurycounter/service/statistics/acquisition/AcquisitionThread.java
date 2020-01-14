package com.jbuelow.injurycounter.service.statistics.acquisition;

import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import lombok.Getter;

public abstract class AcquisitionThread extends Thread {

  @Getter
  private final StatComponent component;

  protected AcquisitionThread(
      StatComponent component) {
    this.component = component;
  }
}
