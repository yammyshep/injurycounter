package com.jbuelow.injurycounter.service.statistics.handler;

import com.jbuelow.injurycounter.service.statistics.data.Statistic;

public interface StatisticHandler<T extends Statistic> {

  void acquireData();

  T getResult();

  Class<? extends Statistic> getTargetClass();

}
