package com.jbuelow.injurycounter.service.cec;

import com.jbuelow.injurycounter.hardware.cec.CECInterface;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class DisplayPowerStateManagementService {

  private final CECInterface cec;

  public DisplayPowerStateManagementService(CECInterface cec) {
    this.cec = cec;
  }

  @Scheduled(cron = "0 15 7 ? * MON-FRI")
  public void powerOnDisplayJob() throws IOException {
    log.info("Turning display on...");
    cec.setPowerState(0, true);
  }

  @Scheduled(cron = "0 30 15 ? * MON-FRI")
  public void powerOffDisplayJob() throws IOException {
    log.info("Turning display off...");
    cec.setPowerState(0, false);
  }

}
