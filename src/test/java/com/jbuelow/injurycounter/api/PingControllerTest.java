package com.jbuelow.injurycounter.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PingControllerTest {

  @InjectMocks
  private PingController controller;

  @Test
  void ping() {
    String response = controller.ping();

    log.debug("PingController responded with '{}'", response);

    assertEquals(response, "{\"online\": true}");
  }
}