package com.jbuelow.injurycounter.api.database;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.entity.Team;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import com.jbuelow.injurycounter.data.repo.TeamRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/db/add")
public class ManualAddController {

  private final PersonRepository personRepo;
  private final InjuryRepository injuryRepo;
  private final AccessLogRepository accessRepo;
  private final TeamRepository teamRepo;

  public ManualAddController(PersonRepository personRepo,
      InjuryRepository injuryRepo,
      AccessLogRepository accessRepo, TeamRepository teamRepo) {
    this.personRepo = personRepo;
    this.injuryRepo = injuryRepo;
    this.accessRepo = accessRepo;
    this.teamRepo = teamRepo;
  }

  @PostMapping("/person")
  public @ResponseBody Person addPerson(HttpServletRequest httpReq, @RequestBody Person person) {
    personRepo.save(person);
    accessRepo.save(AccessLog.forPerson(person, httpReq));
    return person;
  }

  @PostMapping("/injury")
  public @ResponseBody Injury addInjury(HttpServletRequest httpReq, @RequestBody Injury injury) {
    injuryRepo.save(injury);
    accessRepo.save(AccessLog.forInjury(injury, httpReq));
    return injury;
  }

  @PostMapping("/team")
  public @ResponseBody Team addTeam(HttpServletRequest httpReq, @RequestBody Team team) {
    teamRepo.save(team);
    accessRepo.save(AccessLog.forTeam(team, httpReq));
    return team;
  }

}
