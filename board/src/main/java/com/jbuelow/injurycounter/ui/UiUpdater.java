package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.ui.component.live.InjuryDetails;
import com.jbuelow.injurycounter.ui.component.live.Timer;
import java.util.ArrayList;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class UiUpdater {

  private final InjuryRepository injuryRepo;
  private final Timer timer;
  private final InjuryDetails injuryDetails;

  private Injury lastInjury = new Injury();

  public UiUpdater(InjuryRepository injuryRepo,
      Timer timer, InjuryDetails injuryDetails) {
    this.injuryRepo = injuryRepo;
    this.timer = timer;
    this.injuryDetails = injuryDetails;
  }

  @Scheduled(fixedRate = 1000)
  public void checkDB() {
    ArrayList<Injury> injuries = (ArrayList<Injury>) injuryRepo.findAll();
    Collections.sort(injuries);
    Injury injury = injuries.get(injuries.size()-1);
    if (!Injury.isEqual(injury, lastInjury)) {
      log.debug("New injury candidate found with id {}.", injury.getId());
      setLastInjury(injury);
      lastInjury = injury;
    }
  }

  private void setLastInjury(Injury injury) {
    if (injury.isHidden()) {
      log.debug("Injury #{} is hidden. Cancelling ui update.", injury.getId());
      return;
    }
    timer.setLastInjury(injury.getTimestamp().toInstant());
    injuryDetails.setInjuryDetails(injury);
  }

}
