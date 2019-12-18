package com.jbuelow.injurycounter.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

  @CrossOrigin(origins = "*")
  @GetMapping("/ping")
  public @ResponseBody String ping() {
    return "{\"online\": true}";
  }

}
