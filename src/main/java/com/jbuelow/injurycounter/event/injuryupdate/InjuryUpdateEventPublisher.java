package com.jbuelow.injurycounter.event.injuryupdate;

import com.jbuelow.injurycounter.data.entity.Injury;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InjuryUpdateEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public InjuryUpdateEventPublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void publish(Injury injury) {
    log.debug("Publishing injury update event for injury with id {}", injury.getId());
    InjuryUpdateEvent e = new InjuryUpdateEvent(this, injury);
    applicationEventPublisher.publishEvent(e);
  }

}
