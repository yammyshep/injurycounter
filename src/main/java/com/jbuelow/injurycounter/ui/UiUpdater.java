package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.ui.component.live.Timer;
import java.util.Collection;
import java.util.stream.StreamSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class UiUpdater {

  private final InjuryRepository injuryRepo;
  private final Timer timer;

  private long lastInjuryCount = 0;

  public UiUpdater(InjuryRepository injuryRepo,
      Timer timer) {
    this.injuryRepo = injuryRepo;
    this.timer = timer;
  }

  @Scheduled(fixedRate = 1000)
  public void checkDB() {
    Iterable<Injury> injuries = injuryRepo.findAll();
    long injuryCount = StreamSupport.stream(injuries.spliterator(), false).count();
    if (injuryCount != lastInjuryCount) {
      setLastInjury();
    }
  }

  private void setLastInjury() {
    Iterable<Injury> injuries = injuryRepo.findAll();
    Injury latestInjury = ((Collection<Injury>)injuries).stream().reduce((prev, next) -> next).orElse(null);
    assert latestInjury != null;
    timer.setLastInjury(latestInjury.getTimestamp().toInstant());
  }

}
