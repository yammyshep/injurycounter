package com.jbuelow.injurycounter.service.statistics.data.impl;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.service.statistics.data.Statistic;
import java.util.List;

public abstract class TotalInjuries implements Statistic {

  public abstract Long getInjuryCount();

  public abstract List<Injury> getCountedInjuries();
}
