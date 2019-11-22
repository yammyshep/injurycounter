package com.jbuelow.injurycounter.api.database;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
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

  public DropRecordController(PersonRepository personRepo,
      InjuryRepository injuryRepo,
      AccessLogRepository accessRepo) {
    this.personRepo = personRepo;
    this.injuryRepo = injuryRepo;
    this.accessRepo = accessRepo;
  }

  @PostMapping("/person")
  public @ResponseBody Person dropPerson(HttpServletRequest httpReq, @RequestBody Person person) {
    assert person != null;
    for (AccessLog l : accessRepo.findAll()) {
      if (l.getInjury().getId().equals(person.getId())) {
        l.setInjury(null);
        accessRepo.save(l);
      }
    }
    personRepo.delete(person);
    return person;
  }

  @PostMapping("/injury")
  public @ResponseBody Injury dropInjury(HttpServletRequest httpReq, @RequestBody Injury injury) {
    assert injury != null;
    for (AccessLog l : accessRepo.findAll()) {
      if (l.getInjury().getId().equals(injury.getId())) {
        l.setInjury(null);
        accessRepo.save(l);
      }
    }
    injuryRepo.delete(injury);
    return injury;
  }

}
