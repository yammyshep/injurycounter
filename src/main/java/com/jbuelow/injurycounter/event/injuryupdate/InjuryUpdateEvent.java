package com.jbuelow.injurycounter.event.injuryupdate;

import com.jbuelow.injurycounter.data.entity.Injury;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class InjuryUpdateEvent extends ApplicationEvent {

  @Getter
  private Injury injury;

  public InjuryUpdateEvent(Object source, Injury injury) {
    super(source);
    this.injury = injury;
  }

}
