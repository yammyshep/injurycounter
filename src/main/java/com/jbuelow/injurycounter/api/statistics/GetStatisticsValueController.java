package com.jbuelow.injurycounter.api.statistics;

import com.jbuelow.injurycounter.service.statistics.StatisticsService;
import com.jbuelow.injurycounter.service.statistics.data.Statistic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/stat")
@RestController
public class GetStatisticsValueController {

  private final StatisticsService statService;

  public GetStatisticsValueController(
      StatisticsService statService) {
    this.statService = statService;
  }

  @GetMapping("/list")
  public List<Class<?>> getClassifierListing() {
    return new ArrayList<>(statService.getHandlers());
  }

  @GetMapping("/value/{statClass}")
  public Statistic getValue(@PathVariable String statClass) {
    Statistic s = null;
    try {
      s = statService.getResult((Class<? extends Statistic>) Class.forName(statClass));
    } catch (ClassNotFoundException e) {
      throw new StatNotFoundException();
    }
    return s;
  }

  @GetMapping("/value")
  public Map<Class<? extends Statistic>, Statistic> getAllValues() {
    Map<Class<? extends Statistic>, Statistic> stats = new HashMap<>();
    statService.getHandlers().forEach(h -> stats.put(h, statService.getResult(h)));
    return stats;
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stat definition not found")
  public class StatNotFoundException extends RuntimeException {

  }

}