package com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined;

import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResolutionDeterminedEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public ResolutionDeterminedEventPublisher(
      ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void publish(DisplaySizing displaySizing) {
    log.info("Publishing dynamic display resize event with resolution of {}p", displaySizing.displayPercent(100));
    if (displaySizing.displayPercent(100)<100) {
      log.error("Screen size of {} is not valid", displaySizing.displayPercent(100));
      log.error("Restarting...");
      System.exit(0);
    }
    ResolutionDeterminedEvent e = new ResolutionDeterminedEvent(this, displaySizing);
    applicationEventPublisher.publishEvent(e);
  }

}
