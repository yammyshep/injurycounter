package com.jbuelow.injurycounter.ui.component.clock;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class ClockTest {

  private final Clock clock = new Clock();

  @Test
  void getHumanReadableDateAndTime() {
    Instant instant = Instant.now();
    String output = clock.getHumanReadableDateAndTime(instant);
    assertEquals(output.getClass(), String.class);
  }

}