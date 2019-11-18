package com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined;

import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ResolutionDeterminedEvent extends ApplicationEvent {

  @Getter
  private final DisplaySizing sizing;

  public ResolutionDeterminedEvent(Object source, DisplaySizing sizing) {
    super(source);
    this.sizing = sizing;
  }
}
