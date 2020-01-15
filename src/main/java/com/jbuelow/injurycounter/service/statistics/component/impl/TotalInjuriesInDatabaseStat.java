package com.jbuelow.injurycounter.service.statistics.component.impl;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.service.statistics.acquisition.AcquisitionThread;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import java.util.ArrayList;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class TotalInjuriesInDatabaseStat implements StatComponent<Injury, Double> {

  private final InjuryRepository injuryRepo;

  @Getter
  private volatile Double value;

  @Getter
  private volatile ArrayList<Injury> consideredData;

  @Getter
  private final Boolean nightly = false;

  public TotalInjuriesInDatabaseStat(InjuryRepository injuryRepo) {
    this.injuryRepo = injuryRepo;
  }

  @Override
  public AcquisitionThread generateThread() {
    ArrayList<Injury> data = (ArrayList<Injury>) injuryRepo.findAll();
    AcquisitionThread thread = new StatThread(this, data);
    return thread;
  }

  private class StatThread extends AcquisitionThread {

    private final ArrayList<Injury> data;

    StatThread(StatComponent comp, ArrayList<Injury> data) {
      super(comp);
      this.data = data;
    }

    @Override
    public void run() {
      value = (double) data.size();
      consideredData = data;
    }

  }

}
