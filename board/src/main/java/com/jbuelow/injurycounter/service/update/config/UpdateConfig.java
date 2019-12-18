package com.jbuelow.injurycounter.service.update.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "injury-counter.update")
public class UpdateConfig {

  private Boolean enabled;
  private UpdateConfigURL url;

}
