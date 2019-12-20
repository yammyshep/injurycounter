package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.event.injuryupdate.InjuryUpdateEvent;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.time.Duration;
import java.time.Instant;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class Timer extends JLabel {

  @Setter
  private Instant lastInjury = Instant.now();

  public Timer() {
    super("", SwingConstants.CENTER);
  }

  @PostConstruct
  public void init() {
    setText("TIME");
  }

  @Override
  public void paintComponent(Graphics g) {
    String text = getHumanReadableDiff(lastInjury);
    setText(text);
    log.trace("Setting new timer text to {}", text);
    super.paintComponent(g);
  }

  @Deprecated
  public String getHumanReadableTimeSinceInstant() {
    Instant now = Instant.now();
    Duration duration = Duration.between(lastInjury, now);
    long mills = duration.toMillis();
    if (mills < 1000) {
      return Long.toString(mills)+" milliseconds";
    }
    if (mills < 60000) {
      return Long.toString(mills/1000)+" seconds";
    }
    if (mills < 3600000) {
      return mills/60000+" minutes, "+(mills%60000)/1000+" seconds";
    }
    if (mills < 86400000) {
      return mills/3600000+" hours, "+(mills%3600000)/60000+" minutes, "+((mills%3600000)%60000)/1000+" seconds";
    }
    return mills/86400000+" days, "+(mills%86400000)/3600000+" hours";
  }

  private static String getHumanReadableDiff(Instant lastInjury) {
    Instant now = Instant.now();
    Duration duration = Duration.between(lastInjury, now);
    long absSeconds = Math.abs(duration.toMillis());
    String positive = String.format(
        "<html><center>%d day"+(86400000>absSeconds||absSeconds>172800000?"s":"")+"<br>%02d:%02d:%02d</center></html>",
        absSeconds / 86400000,
        (absSeconds % 86400000) / 3600000,
        (absSeconds % 3600000) / 60000,
        (absSeconds % 60000) / 1000);
    return absSeconds < 0 ? "-" + positive : positive;
  }

  @Scheduled(fixedRate = 20)
  public void refreshTimer() {
    repaint();
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(15f)));
    }

  }

  @Component
  private class InjuryUpdateEventListener implements
      ApplicationListener<InjuryUpdateEvent> {

    @Override
    public void onApplicationEvent(InjuryUpdateEvent injuryUpdateEvent) {
      setLastInjury(injuryUpdateEvent.getInjury().getTimestamp().toInstant());
    }

  }

}
