package com.jbuelow.injurycounter.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.jbuelow.injurycounter.ui.component.live.Timer;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResetInjuryCounterControllerTest {

  @InjectMocks
  private ResetInjuryCounterController controller;

  @Mock
  private Timer timer;

  @Test
  void resetCounter() {
    controller.resetCounter();
    verify(timer).setLastInjury(any(Instant.class));
  }
}