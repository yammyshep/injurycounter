package com.jbuelow.injurycounter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetInjuryCounterController {

  //private final Timer timer;

  public ResetInjuryCounterController() {
    //this.timer = timer;
  }

  @GetMapping("/api/reset")
  public void resetCounter() {
    //timer.setLastInjury(Instant.now());
  }

}
