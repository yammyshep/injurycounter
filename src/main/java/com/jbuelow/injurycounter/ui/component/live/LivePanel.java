package com.jbuelow.injurycounter.ui.component.live;

import java.awt.BorderLayout;
import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

@Component
public class LivePanel extends JPanel {

  private final LiveTitle liveTitle;
  private final Timer timer;
  private final InjuryDetails injuryDetails;

  public LivePanel(LiveTitle liveTitle, Timer timer,
      InjuryDetails injuryDetails) {
    this.liveTitle = liveTitle;
    this.timer = timer;
    this.injuryDetails = injuryDetails;
  }

  @PostConstruct
  public void init() {
    setLayout(new BorderLayout());
    add(liveTitle, BorderLayout.NORTH);
    add(timer, BorderLayout.CENTER);
    add(injuryDetails, BorderLayout.SOUTH);
  }

}
