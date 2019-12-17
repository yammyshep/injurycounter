package com.jbuelow.injurycounter.api.database;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.entity.Team;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import com.jbuelow.injurycounter.data.repo.TeamRepository;
import java.util.Iterator;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/db/drop")
public class DropRecordController {

  private final PersonRepository personRepo;
  private final InjuryRepository injuryRepo;
  private final AccessLogRepository accessRepo;
  private final TeamRepository teamRepo;

  public DropRecordController(PersonRepository personRepo,
      InjuryRepository injuryRepo,
      AccessLogRepository accessRepo, TeamRepository teamRepo) {
    this.personRepo = personRepo;
    this.injuryRepo = injuryRepo;
    this.accessRepo = accessRepo;
    this.teamRepo = teamRepo;
  }

  @PostMapping("/person")
  public @ResponseBody Person dropPerson(HttpServletRequest httpReq, @RequestBody Person person) {
    assert person != null;
    Iterator<AccessLog> logs = accessRepo.findAll().iterator();
    while (logs.hasNext()) {
      AccessLog l = logs.next();
      if (Objects.isNull(l.getPerson())) {
        continue;
      }
      if (l.getPerson().getId().equals(person.getId())) {
        l.setPerson(null);
        accessRepo.save(l);
      }
    }
    personRepo.delete(person);
    return person;
  }

  @PostMapping("/injury")
  public @ResponseBody Injury dropInjury(HttpServletRequest httpReq, @RequestBody Injury injury) {
    assert injury != null;
    Iterator<AccessLog> logs = accessRepo.findAll().iterator();
    while (logs.hasNext()) {
      AccessLog l = logs.next();
      if (Objects.isNull(l.getInjury())) {
        continue;
      }
      if (l.getInjury().getId().equals(injury.getId())) {
        l.setInjury(null);
        accessRepo.save(l);
      }
    }
    injuryRepo.delete(injury);
    return injury;
  }

  @PostMapping("/team")
  public @ResponseBody Team dropTeam(HttpServletRequest httpReq, @RequestBody Team team) {
    Iterator<AccessLog> logs = accessRepo.findAll().iterator();
    while (logs.hasNext()) {
      AccessLog l = logs.next();
      if (Objects.isNull(l.getTeam())) {
        continue;
      }
      if (l.getTeam().getId().equals(team.getId())) {
        l.setTeam(null);
        accessRepo.save(l);
      }
    }
    teamRepo.delete(team);
    return team;
  }

}
