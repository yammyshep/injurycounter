package com.jbuelow.injurycounter.api.database;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.entity.Team;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import com.jbuelow.injurycounter.data.repo.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/db/dump")
public class DumpTableController {

  private final InjuryRepository injuryRepo;
  private final PersonRepository personRepo;
  private final AccessLogRepository accessRepo;
  private final TeamRepository teamRepo;

  public DumpTableController(InjuryRepository injuryRepo,
      PersonRepository personRepo,
      AccessLogRepository accessRepo, TeamRepository teamRepo) {
    this.injuryRepo = injuryRepo;
    this.personRepo = personRepo;
    this.accessRepo = accessRepo;
    this.teamRepo = teamRepo;
  }

  @GetMapping("/injury")
  public @ResponseBody Iterable<Injury> dumpInjuries() {
    return injuryRepo.findAll();
  }

  @GetMapping("/person")
  public @ResponseBody Iterable<Person> dumpPersons() {
    return personRepo.findAll();
  }

  @GetMapping("/access")
  public @ResponseBody Iterable<AccessLog> dumpAccessLog() {
    return accessRepo.findAll();
  }

  @GetMapping("/team")
  public @ResponseBody Iterable<Team> dumpTeams() {
    return teamRepo.findAll();
  }

}
