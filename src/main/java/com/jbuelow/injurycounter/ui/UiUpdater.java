package com.jbuelow.injurycounter.ui;

import java.awt.Frame;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class UiUpdater {

  private final InjuryTimeDisplay injuryTimeDisplay;

  public UiUpdater(InjuryTimeDisplay injuryTimeDisplay) {
    this.injuryTimeDisplay = injuryTimeDisplay;
  }



}
