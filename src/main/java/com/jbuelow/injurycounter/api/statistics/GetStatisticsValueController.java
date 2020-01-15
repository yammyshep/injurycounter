package com.jbuelow.injurycounter.api.statistics;

import com.jbuelow.injurycounter.service.statistics.StatisticsService;
import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import java.util.List;
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
  public List<Class> getClassifierListing() {
    return statService.getStats();
  }

  @GetMapping("/value/{statClass}")
  public Object getValue(@PathVariable String statClass) {
    StatComponent comp = null;
    try {
      comp = statService.getStat(Class.forName(statClass));
    } catch (ClassNotFoundException e) {
      throw new StatNotFoundException();
    }
    return comp.getValue();
  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stat definition not found")
  public class StatNotFoundException extends RuntimeException {

  }

}
