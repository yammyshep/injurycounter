package com.jbuelow.injurycounter.service;

import com.jbuelow.injurycounter.fxui.FxUiController;
import com.jbuelow.injurycounter.ui.component.live.Timer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import javafx.application.Platform;
import javafx.scene.control.Label;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class ClockUpdateScheduledService {

  private final FxUiController fxUiController;

  @Setter
  private Instant lastInjuryInstant = Instant.now();

  public ClockUpdateScheduledService(FxUiController fxUiController) {
    this.fxUiController = fxUiController;
  }

  @Scheduled(fixedRate = 500)
  public void updateDateTimeClock() {
    Label l = fxUiController.getDateTime();
    if (l == null) {
      log.warn("Label object is null. Maybe the UI has not loaded yet?");
      return;
    }
    String dateTime = new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss aa").format(Timestamp.from(Instant.now()));
    Platform.runLater(() -> l.setText(dateTime));
  }

  @Scheduled(fixedRate = 20)
  public void updateTimeSinceInjuryClock() {
    Label l = fxUiController.getTimeSinceInjury();
    if (l == null) {
      log.warn("Label object is null. Maybe the UI has not loaded yet?");
      return;
    }
    String timeString = Timer.getHumanReadableTimeSinceInstant(lastInjuryInstant);
    Platform.runLater(() -> l.setText(timeString) );
  }

}
