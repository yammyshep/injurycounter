package com.jbuelow.injurycounter.ui.popup.newinjury;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.event.injuryupdate.InjuryUpdateEvent;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import com.jbuelow.injurycounter.ui.popup.newinjury.compnent.FooterPanel;
import com.jbuelow.injurycounter.ui.popup.newinjury.compnent.NamePanel;
import com.jbuelow.injurycounter.ui.popup.newinjury.compnent.TitlePanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useUI")
public class NewInjuryWindow extends JFrame {

  private final TitlePanel titlePanel;
  private final NamePanel namePanel;
  private final FooterPanel footerPanel;

  public NewInjuryWindow(
      TitlePanel titlePanel,
      NamePanel namePanel,
      FooterPanel footerPanel) {
    this.titlePanel = titlePanel;
    this.namePanel = namePanel;
    this.footerPanel = footerPanel;
  }

  public void showForInjury(Injury injury) {
    setVisible(true);
    Timer t = new Timer();
    t.schedule(new HideTimerTask(), 30000);
  }

  private class HideTimerTask extends TimerTask {

    @Override
    public void run() {
      setVisible(false);
    }

  }

  @PostConstruct
  public void init() {
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setLayout(new BorderLayout());
    add(titlePanel, BorderLayout.PAGE_START);
    add(namePanel, BorderLayout.CENTER);
    add(footerPanel, BorderLayout.PAGE_END);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      setLocationRelativeTo(null);
    }

  }

  @Component
  private class InjuryUpdateEventListener implements
      ApplicationListener<InjuryUpdateEvent> {

    @Override
    public void onApplicationEvent(InjuryUpdateEvent injuryUpdateEvent) {
      if (Objects.nonNull(injuryUpdateEvent.getLastInjury())) {
        showForInjury(injuryUpdateEvent.getInjury());
      }
    }

  }

}
