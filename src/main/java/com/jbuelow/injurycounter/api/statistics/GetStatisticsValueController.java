package com.jbuelow.injurycounter.api.statistics;

import com.jbuelow.injurycounter.service.statistics.StatisticsService;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/stat")
@RestController
public class GetStatisticsValueController {

  private final StatisticsService statService;

  public GetStatisticsValueController(
      StatisticsService statService) {
    this.statService = statService;
  }

  @GetMapping("/value/{statClass}")
  public Object getValue(@PathVariable String statClass) throws ClassNotFoundException {
    StatComponent comp = statService.getStat(Class.forName(statClass));
    return comp.getValue();
  }

}
