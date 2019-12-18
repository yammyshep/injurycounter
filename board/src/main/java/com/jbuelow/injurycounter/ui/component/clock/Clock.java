package com.jbuelow.injurycounter.ui.component.clock;

import java.awt.Font;
import java.awt.Graphics;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class Clock extends JPanel {

  private final JLabel line1 = new JLabel();
  private final JLabel line2 = new JLabel();

  @PostConstruct
  public void init() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(line1);
    add(line2);
    line1.setText("TIME");
    line2.setText("TIME");
    line1.setFont(new Font(getFont().getName(), getFont().getStyle(), 24));
    line2.setFont(new Font(getFont().getName(), getFont().getStyle(), 24));
  }

  @Override
  public void paintComponent(Graphics g) {
    Instant now = Instant.now();
    String line1 = getHumanReadableDateAndTime(now);
    this.line1.setText(line1);
    log.trace("Setting new clock line 1 text to {}", line1);
    String line2 = getHumanReadableSecondLine(now);
    this.line2.setText(line2);
    log.trace("Setting new clock line 2 text to {}", line2);
    super.paintComponent(g);
  }

  public String getHumanReadableDateAndTime(Instant time) {
    return new SimpleDateFormat("EEEEEEEEEEEE, MMMMMMMMMMMM dd").format(new Timestamp(time.toEpochMilli()));
  }

  public String getHumanReadableSecondLine(Instant time) {
    return new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss aa").format(new Timestamp(time.toEpochMilli()));
  }

  @Scheduled(fixedRate = 20)
  public void refreshTimer() {
    repaint();
  }


}
