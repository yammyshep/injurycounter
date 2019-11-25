package com.jbuelow.injurycounter.ui;

import com.jbuelow.injurycounter.ui.component.instructions.InstructionsPanel;
import com.jbuelow.injurycounter.ui.component.live.LivePanel;
import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEventPublisher;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("useUI")
public class InjuryTimeDisplay extends JFrame {

  private final LivePanel livePanel;
  private final InstructionsPanel instructionsPanel;
  private final ResolutionDeterminedEventPublisher rdep;

  private final JTabbedPane tabPane = new JTabbedPane();
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
    tabPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI(){
      @Override
      protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
        return 0;
      }
      protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
    });
    add(tabPane);
    tabPane.addTab("live", livePanel);
    tabPane.addTab("instructions", instructionsPanel);
  }



}
