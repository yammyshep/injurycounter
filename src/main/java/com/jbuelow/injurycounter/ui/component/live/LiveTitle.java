package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LiveTitle extends JLabel {

  public LiveTitle() {
    super("", SwingConstants.CENTER);
  }

  @PostConstruct
  public void init() {
    setText("Time Since Injury");
  }

  @Component
  private class ResolutionDeterminedEventListener implements ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(12f)));
    }

  }

}
