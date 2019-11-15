package com.jbuelow.injurycounter.ui.component.live;

import java.awt.Font;
import java.awt.Graphics;
import java.time.Duration;
import java.time.Instant;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Component
@EnableScheduling
public class Timer extends JLabel {

  @Setter
  private Instant lastInjury = Instant.now();

  @PostConstruct
  public void init() {
    setText("TIME");
    setFont(new Font(getFont().getName(), getFont().getStyle(), 45));
  }

  @Override
  public void paintComponent(Graphics g) {
    String text = getHumanReadableTimeSinceInstant();
    setText(text);
    log.trace("Setting new timer text to {}", text);
    super.paintComponent(g);
  }

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

  @Scheduled(fixedRate = 20)
  public void refreshTimer() {
    repaint();
  }

}
