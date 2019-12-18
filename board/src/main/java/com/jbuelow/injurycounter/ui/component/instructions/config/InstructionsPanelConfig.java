package com.jbuelow.injurycounter.ui.component.instructions.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "injury-counter.instructions")
public class InstructionsPanelConfig {

  private String wifiNetwork = "???";
  private String webAddress = "???";

}
