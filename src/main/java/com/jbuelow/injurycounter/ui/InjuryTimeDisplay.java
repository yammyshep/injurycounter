package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.ui.component.history.HistoryPanel;
import com.jbuelow.injurycounter.ui.component.live.LivePanel;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
    try {
      setVisible(true);
    } catch (HeadlessException e) {
      log.error("========================================================\n"
              + "===== We are in headless awt mode. ui not started. =====\n"
              + "========================================================");
    }
  }

  private void addComponents() {
    add(livePanel);
    add(historyPanel);
  }



}
