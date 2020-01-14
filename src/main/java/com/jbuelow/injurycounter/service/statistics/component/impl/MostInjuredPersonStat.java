package com.jbuelow.injurycounter.service.statistics.component.impl;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.service.statistics.acquisition.AcquisitionThread;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class MostInjuredPersonStat implements StatComponent<Injury, Entry<Person, Integer>> {

  private final InjuryRepository injuryRepo;

  @Getter
  private volatile Entry<Person, Integer> value;

  @Getter
  private volatile ArrayList<Injury> consideredData;

  @Getter
  @Setter
  private final Boolean nightly = false;

  public MostInjuredPersonStat(InjuryRepository injuryRepo) {
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
      Map<Person, Integer> people = new HashMap<>();
      data.forEach(injury -> {
        Integer c = people.get(injury.getPerson());
        people.put(injury.getPerson(), (c == null) ? 1 : c + 1);
      });
      Map.Entry<Person, Integer> maxEntry = null;

      for (Map.Entry<Person, Integer> entry : people.entrySet()) {
        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
          maxEntry = entry;
        }

      }

      value = maxEntry;

    }

  }
}
