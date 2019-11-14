package com.jbuelow.injurycounter.service;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.fxui.FxUiController;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class DatabaseCheckerScheduledService {

  private final InjuryRepository injuryRepository;
  private final FxUiController uiController;
  private final ClockUpdateScheduledService clockService;

  private Timestamp lastLatestInjuryTimestamp = Timestamp.from(Instant.EPOCH);

  public DatabaseCheckerScheduledService(
      InjuryRepository injuryRepository, FxUiController uiController,
      ClockUpdateScheduledService clockService) {
    this.injuryRepository = injuryRepository;
    this.uiController = uiController;
    this.clockService = clockService;
  }

  // Run every minute
  @Scheduled(fixedRate = 1000)
  public void pollDB() {
    List<Injury> injuries = (List<Injury>) injuryRepository.findAll();
    Collections.sort(injuries);
    Injury latestInjury = injuries.get(injuries.size()-1);
    if (lastLatestInjuryTimestamp.compareTo(latestInjury.getTimestamp()) < 0) {
      lastLatestInjuryTimestamp = latestInjury.getTimestamp();
      sendInjuryToUi(latestInjury);
    }
  }

  private void sendInjuryToUi(Injury latestInjury) {
    uiController.updateInjury(latestInjury);
    clockService.setLastInjuryInstant(latestInjury.getTimestamp().toInstant());
  }
}
