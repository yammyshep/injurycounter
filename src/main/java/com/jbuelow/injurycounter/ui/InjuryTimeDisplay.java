package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.ui.component.history.HistoryPanel;
import com.jbuelow.injurycounter.ui.component.live.LivePanel;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import org.springframework.stereotype.Component;

@Component
public class InjuryTimeDisplay extends JFrame {

  private final LivePanel livePanel;
  private final HistoryPanel historyPanel;

  public InjuryTimeDisplay(LivePanel livePanel,
      HistoryPanel historyPanel) {
    this.livePanel = livePanel;
    this.historyPanel = historyPanel;
  }

  @PostConstruct
  public void init() {
    //setAlwaysOnTop(true); //our frame can never bottom
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setLayout(new GridLayout());
    addComponents();
    setVisible(true);
  }

  private void addComponents() {
    add(livePanel);
    add(historyPanel);
  }



}
