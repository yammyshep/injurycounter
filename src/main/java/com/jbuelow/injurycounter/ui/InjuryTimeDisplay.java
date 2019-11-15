package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.ui.component.live.LivePanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("useUI")
public class InjuryTimeDisplay extends JFrame {

  private final LivePanel livePanel;
  //private final HistoryPanel historyPanel;

  public InjuryTimeDisplay(LivePanel livePanel/*,
      HistoryPanel historyPanel*/) {
    this.livePanel = livePanel;
    //this.historyPanel = historyPanel;
  }

  @PostConstruct
  public void init() {
    //setAlwaysOnTop(true); //our frame can never bottom
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setLayout(new BorderLayout());
    addComponents();
    setVisible(true);
  }

  private void addComponents() {
    add(livePanel, BorderLayout.CENTER);
    //add(historyPanel);
  }



}
