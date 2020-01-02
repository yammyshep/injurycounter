package com.jbuelow.injurycounter.ui.popup.newinjury.compnent;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Team;
import com.jbuelow.injurycounter.event.injuryupdate.InjuryUpdateEvent;
import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useUI")
public class NamePanel extends JPanel {

  private final JLabel nameLabel = new JLabel("John Doe", SwingConstants.CENTER);

  @PostConstruct
  public void init() {
    setLayout(new BorderLayout());
    add(nameLabel, BorderLayout.CENTER);
  }

  public void setFromInjury(Injury injury) {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><center>");
    sb.append(Team.toHtmlFlag(injury.getPerson().getTeam()));
    sb.append(injury.getPerson().getName());
    if (Objects.nonNull(injury.getInstigator())) {
      sb.append("<br>&<br>");
      sb.append(Team.toHtmlFlag(injury.getInstigator().getTeam()));
      sb.append(injury.getInstigator().getName());
    }
    sb.append("</center></html>");
    nameLabel.setText(sb.toString());
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      DisplaySizing ds = resolutionDeterminedEvent.getSizing();
      nameLabel.setFont(new Font(nameLabel.getFont().getName(), nameLabel.getFont().getStyle(), ds.displayPercent(10)));
    }

  }

  @Component
  private class InjuryUpdateEventListener implements
      ApplicationListener<InjuryUpdateEvent> {

    @Override
    public void onApplicationEvent(InjuryUpdateEvent injuryUpdateEvent) {
      Injury injury = injuryUpdateEvent.getInjury();
      setFromInjury(injury);
    }

  }

}
