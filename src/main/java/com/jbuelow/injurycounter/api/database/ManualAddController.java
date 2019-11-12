package com.jbuelow.injurycounter.api.database;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
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

  public ManualAddController(PersonRepository personRepo,
      InjuryRepository injuryRepo) {
    this.personRepo = personRepo;
    this.injuryRepo = injuryRepo;
  }

  @PostMapping("/person")
  public @ResponseBody Person addPerson(@RequestBody Person person) {
    assert person != null;
    personRepo.save(person);
    return person;
  }

  @PostMapping("/injury")
  public @ResponseBody Injury addInjury(@RequestBody Injury injury) {
    assert injury != null;
    injuryRepo.save(injury);
    return injury;
  }

}
