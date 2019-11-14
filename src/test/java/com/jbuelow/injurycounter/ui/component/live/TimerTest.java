package com.jbuelow.injurycounter.ui.component.live;

import static com.jbuelow.injurycounter.ui.component.live.Timer.getHumanReadableTimeSinceInstant;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class TimerTest {

  @Test
  void getTime10sec() {
    String out = getHumanReadableTimeSinceInstant(Instant.now().minusSeconds(10));
    assertEquals(out, "10 seconds");
  }

  @Test
  void getTime10min10sec() {
    String out = getHumanReadableTimeSinceInstant(Instant.now().minusSeconds(10).minusSeconds(600));
    assertEquals(out, "10 minutes, 10 seconds");
  }

}