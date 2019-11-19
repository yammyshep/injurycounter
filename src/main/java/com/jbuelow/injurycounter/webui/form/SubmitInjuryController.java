package com.jbuelow.injurycounter.webui.form;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmitInjuryController {

  private final InjuryRepository injuryRepo;
  private final AccessLogRepository accessRepo;

  public SubmitInjuryController(InjuryRepository injuryRepo,
      AccessLogRepository accessRepo) {
    this.injuryRepo = injuryRepo;
    this.accessRepo = accessRepo;
  }

  @GetMapping("/submit")
  public String getForm(Model model) {
    return "reportInjury";
  }

  @PostMapping("/submit")
  public String postForm(HttpServletRequest httpReq, Model model, Injury injury) {
    assert injury != null;
    injuryRepo.save(injury);
    accessRepo.save(AccessLog.forInjury(injury, httpReq));
    return "success";
  }

}
