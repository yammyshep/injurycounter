package com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined;

import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import java.awt.Dimension;
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
    if (displaySizing.displayPercent(100)<100) {
      log.warn("Screen size of {}p is not valid! Assuming new size of 1080p", displaySizing.displayPercent(100));
      displaySizing.setDisplayDimension(new Dimension(1920,1080));
    }
    log.info("Publishing dynamic display resize event with resolution of {}p", displaySizing.displayPercent(100));
    ResolutionDeterminedEvent e = new ResolutionDeterminedEvent(this, displaySizing);
    applicationEventPublisher.publishEvent(e);
  }

}
