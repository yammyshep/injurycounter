package com.jbuelow.injurycounter.service.statistics.component.impl;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.service.statistics.acquisition.AcquisitionThread;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class LongestStandingInjuryStat implements StatComponent<Injury, Injury> {

  private final InjuryRepository injuryRepo;

  @Getter
  private volatile Injury value;

  @Getter
  private volatile Long timeDiff;

  @Getter
  private volatile ArrayList<Injury> consideredData;

  @Getter
  private final Boolean nightly = false;

  public LongestStandingInjuryStat(InjuryRepository injuryRepo) {
    this.injuryRepo = injuryRepo;
  }

  @Override
  public AcquisitionThread generateThread() {
    ArrayList<Injury> data = (ArrayList<Injury>) injuryRepo.findAll();
    return new StatThread(this, data);
  }

  private class StatThread extends AcquisitionThread {

    private final ArrayList<Injury> data;

    StatThread(StatComponent comp, ArrayList<Injury> data) {
      super(comp);
      this.data = data;
    }

    @Override
    public void run() {
      Collections.sort(data);
      Injury record = null;
      long recordT = 0;
      for (Injury test : data) {
        Injury next;
        if (data.indexOf(test) >= data.size() - 1) {
          next = new Injury();
        } else {
          next = data.get(data.indexOf(test) + 1);
        }
        long diff = next.getTimestamp().getTime() - test.getTimestamp().getTime();
        if (diff > recordT) {
          recordT = diff;
          record = test;
        }
      }
      value = record;
      timeDiff = recordT;
    }

  }
}
