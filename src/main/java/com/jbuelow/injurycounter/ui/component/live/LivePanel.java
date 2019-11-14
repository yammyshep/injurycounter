package com.jbuelow.injurycounter.ui.component.live;

import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

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
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(liveTitle);
    add(timer);
    add(injuryDetails);
  }

}
