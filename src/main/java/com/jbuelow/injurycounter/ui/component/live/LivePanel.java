package com.jbuelow.injurycounter.ui.component.live;

import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

@Component
public class LivePanel extends JPanel {

  private final LiveTitle liveTitle;
  private final Timer timer;

  public LivePanel(LiveTitle liveTitle, Timer timer) {
    this.liveTitle = liveTitle;
    this.timer = timer;
  }

  @PostConstruct
  public void init() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(liveTitle);
    add(timer);
  }

}
