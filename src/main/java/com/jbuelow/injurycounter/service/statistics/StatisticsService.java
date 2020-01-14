package com.jbuelow.injurycounter.service.statistics;

import com.jbuelow.injurycounter.service.statistics.acquisition.AcquisitionThread;
import com.jbuelow.injurycounter.service.statistics.acquisition.DataAcquisitionService;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service
@Slf4j
@EnableScheduling
public class StatisticsService implements ApplicationContextAware {

  @Setter
  private ApplicationContext applicationContext;

  private Map<Class, StatComponent> componentMap = new HashMap<>();

  private final DataAcquisitionService acquisitionService;

  public StatisticsService(
      DataAcquisitionService acquisitionService) {
    this.acquisitionService = acquisitionService;
  }

  @PostConstruct
  public void registerStatisticsComponents() {
    componentMap.clear();
    log.debug("Starting statistic component registration");
    for (Map.Entry<String, StatComponent> entry : applicationContext.getBeansOfType(StatComponent.class).entrySet()) {
      log.debug("Found StatComponent: {}", entry.getValue().getClass().getCanonicalName());
      StatComponent component = entry.getValue();
      componentMap.put(component.getClass(), component);
    }
    log.info("Successfully loaded all statistic component classifiers!");
  }

  @Scheduled(fixedRate = 900000)
  public void acquireStats() {
    log.debug("Acquiring statistics...");
    List<AcquisitionThread> threads = new ArrayList<>();
    componentMap.forEach((aClass, component) -> threads.add(component.generateThread()));
    threads.removeIf(thread -> thread.getComponent().getNightly());
    acquisitionService.submitAcquisitions(threads);
  }

  public StatComponent getStat(Class statClass) {
    return componentMap.get(statClass);
  }

}
