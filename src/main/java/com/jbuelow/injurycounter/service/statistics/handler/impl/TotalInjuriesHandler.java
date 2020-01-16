package com.jbuelow.injurycounter.service.statistics.handler.impl;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.service.statistics.data.impl.TotalInjuries;
import com.jbuelow.injurycounter.service.statistics.handler.StatisticHandler;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class TotalInjuriesHandler implements StatisticHandler<TotalInjuries> {

  private final InjuryRepository repository;

  @Getter
  private TotalInjuries result = null;

  public TotalInjuriesHandler(InjuryRepository repository) {
    this.repository = repository;
  }

  @Override
  public void acquireData() {
    result = new TotalInjuries() {
      @Override
      public Long getInjuryCount() {
        return repository.count();
      }

      @Override
      public List<Injury> getCountedInjuries() {
        return (ArrayList<Injury>) repository.findAll();
      }
    };
  }

  @Override
  public Class<TotalInjuries> getTargetClass() {
    return TotalInjuries.class;
  }

}
