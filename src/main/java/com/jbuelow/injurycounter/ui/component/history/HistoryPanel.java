package com.jbuelow.injurycounter.ui.component.history;

import com.jbuelow.injurycounter.ui.component.clock.Clock;
import java.awt.FlowLayout;
import javax.annotation.PostConstruct;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

public class HistoryPanel extends JPanel {

  private final HistoryTitle historyTitle;
  private final Clock clock;

  public HistoryPanel(HistoryTitle historyTitle,
      Clock clock) {
    this.historyTitle = historyTitle;
    this.clock = clock;
  }

  @PostConstruct
  public void init() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(historyTitle);
    add(Box.createVerticalGlue());
    add(clock);
  }

}
