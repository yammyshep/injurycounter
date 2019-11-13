package com.jbuelow.injurycounter.webui.form;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmitInjuryController {

  private final InjuryRepository injuryRepo;

  public SubmitInjuryController(InjuryRepository injuryRepo) {
    this.injuryRepo = injuryRepo;
  }

  @GetMapping("/submit")
  public String getForm(Model model) {
    return "reportInjury";
  }

  @PostMapping("/submit")
  public String postForm(Model model, Injury injury) {
    injuryRepo.save(injury);
    return "success";
  }

}
