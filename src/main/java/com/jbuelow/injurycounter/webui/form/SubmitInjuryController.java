package com.jbuelow.injurycounter.webui.form;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmitInjuryController {

  private final InjuryRepository injuryRepo;
  private final PersonRepository personRepo;
  private final AccessLogRepository accessRepo;

  public SubmitInjuryController(InjuryRepository injuryRepo,
      PersonRepository personRepo,
      AccessLogRepository accessRepo) {
    this.injuryRepo = injuryRepo;
    this.personRepo = personRepo;
    this.accessRepo = accessRepo;
  }

  @GetMapping("/injury")
  public String getForm(Model model) {
    ArrayList<Person> persons = (ArrayList<Person>) personRepo.findAll();
    Collections.sort(persons);
    model.addAttribute("persons", persons);
    return "reportInjury";
  }

  @PostMapping("/injury")
  public String postForm(HttpServletRequest httpReq, Model model, Injury injury) {
    assert injury.getPerson() != null;
    injuryRepo.save(injury);
    accessRepo.save(AccessLog.forInjury(injury, httpReq));
    return "success";
  }

}
