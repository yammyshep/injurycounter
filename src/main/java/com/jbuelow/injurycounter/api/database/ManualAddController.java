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
@RequestMapping("/api/db/add")
public class ManualAddController {

  private final PersonRepository personRepo;
  private final InjuryRepository injuryRepo;
  private final AccessLogRepository accessRepo;

  public ManualAddController(PersonRepository personRepo,
      InjuryRepository injuryRepo,
      AccessLogRepository accessRepo) {
    this.personRepo = personRepo;
    this.injuryRepo = injuryRepo;
    this.accessRepo = accessRepo;
  }

  @PostMapping("/person")
  public @ResponseBody Person addPerson(HttpServletRequest httpReq, @RequestBody Person person) {
    assert person.getId() != null;
    AccessLog al = new AccessLog();
    personRepo.save(person);
    accessRepo.save(AccessLog.forPerson(person, httpReq));
    return person;
  }

  @PostMapping("/injury")
  public @ResponseBody Injury addInjury(HttpServletRequest httpReq, @RequestBody Injury injury) {
    assert injury.getPerson() != null;
    injuryRepo.save(injury);
    accessRepo.save(AccessLog.forInjury(injury, httpReq));
    return injury;
  }

}
