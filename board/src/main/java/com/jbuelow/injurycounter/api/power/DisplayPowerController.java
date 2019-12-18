package com.jbuelow.injurycounter.api.power;

import com.jbuelow.injurycounter.hardware.cec.CECInterface;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/power")
public class DisplayPowerController {

  private final CECInterface cec;

  public DisplayPowerController(CECInterface cec) {
    this.cec = cec;
  }

  @GetMapping("/on")
  public void powerOn() throws IOException {
    cec.setPowerState(0, true);
  }

  @GetMapping("/off")
  public void powerOff() throws IOException {
    cec.setPowerState(0, false);
  }

}
