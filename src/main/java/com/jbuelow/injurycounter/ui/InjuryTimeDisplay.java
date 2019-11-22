package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.ui.component.instructions.InstructionsPanel;
import com.jbuelow.injurycounter.ui.component.live.LivePanel;
import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEventPublisher;
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
  private final InstructionsPanel instructionsPanel;
  private final ResolutionDeterminedEventPublisher rdep;
  //private final HistoryPanel historyPanel;

  public InjuryTimeDisplay(LivePanel livePanel/*,
      HistoryPanel historyPanel*/,
      InstructionsPanel instructionsPanel,
      ResolutionDeterminedEventPublisher rdep) {
    this.livePanel = livePanel;
    this.instructionsPanel = instructionsPanel;
    //this.historyPanel = historyPanel;
    this.rdep = rdep;
  }

  @PostConstruct
  public void init() {
    //setAlwaysOnTop(true); //our frame can never bottom
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setLayout(new BorderLayout());
    addComponents();
    setVisible(true);

    DisplaySizing ds = new DisplaySizing();
    ds.setDisplayDimension(getSize());
    rdep.publish(ds);
  }

  private void addComponents() {
    //add(livePanel, BorderLayout.CENTER);
    add(instructionsPanel, BorderLayout.CENTER);
    //add(historyPanel);
  }



}
