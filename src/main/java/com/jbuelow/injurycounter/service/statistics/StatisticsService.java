package com.jbuelow.injurycounter.service.statistics;

import com.jbuelow.injurycounter.service.statistics.data.Statistic;
import com.jbuelow.injurycounter.service.statistics.handler.StatisticHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class StatisticsService implements ApplicationContextAware {

  @Setter
  private ApplicationContext applicationContext;

  private final ExecutorService executorService = Executors.newFixedThreadPool(2);

  private final Map<Class<? extends Statistic>, StatisticHandler<? extends Statistic>> handlerMap = new HashMap<>();

  @PostConstruct
  public void registerStatistics() {
    log.debug("Starting statistics registration");
    applicationContext.getBeansOfType(StatisticHandler.class).forEach((key, value) -> {
      log.debug("Loading statistic handler {}", value.getClass().getCanonicalName());
      StatisticHandler<? extends Statistic> newVal = value;
      handlerMap.put(newVal.getTargetClass(), newVal);
    });
    log.info("Loaded {} statistic definitions.", handlerMap.size());
  }

  public void updateAllStats() {
    getHandlers().forEach(this::updateStat);
  }

  @PostConstruct
  @Scheduled(cron = "0 0 * * * *")
  public void updateAllStatsAsync() {
    getHandlers().forEach(stat -> executorService.submit(() -> updateStat(stat)));
  }

  public void updateStat(Class<? extends Statistic> stat) {
    log.debug("Updating statistic {}", stat.getCanonicalName());
    getHandler(stat).acquireData();
  }

  public Statistic getResult(Class<? extends Statistic> stat) {
    return getHandler(stat).getResult();
  }

  public StatisticHandler<? extends Statistic> getHandler(Class<? extends Statistic> stat) {
    return handlerMap.get(stat);
  }

  public Set<Class<? extends Statistic>> getHandlers() {
    return handlerMap.keySet();
  }
}
