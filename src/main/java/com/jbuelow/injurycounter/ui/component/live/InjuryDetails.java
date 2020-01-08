package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.event.injuryupdate.InjuryUpdateEvent;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InjuryDetails extends JPanel {

  private final JLabel nameLabel = new JLabel("Joe Smith", SwingConstants.CENTER);
  private final JLabel descriptionLabel = new JLabel("he died", SwingConstants.CENTER);

  @PostConstruct
  public void addComponents() {
    setLayout(new BorderLayout());
    add(nameLabel, BorderLayout.NORTH);
    add(descriptionLabel, BorderLayout.SOUTH);
  }

  @PostConstruct
  public void setFonts() {
    nameLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), 64));
    descriptionLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), 32));
  }

  public void setInjuryDetails(Injury injury) {
    StringBuilder nameSB = new StringBuilder();
    Person instigator = injury.getInstigator();
    Person victim = injury.getPerson();
    nameSB.append("<html>");
    if (instigator != null &&
        instigator != victim) {
      nameSB.append(instigator.getTeam().toHtmlFlag());
      nameSB.append(" ");
      nameSB.append(Optional.ofNullable(instigator.getShortName()).orElse(instigator.getName()));
      nameSB.append("  ")
          .append(nameLabel.getFont().canDisplay('\u2192') ? "\u2192" : ">")
          .append("  ");
      nameSB.append(victim.getTeam().toHtmlFlag());
      nameSB.append(" ");
      nameSB.append(Optional.ofNullable(victim.getShortName()).orElse(victim.getName()));
    } else {
      nameSB.append(victim.getTeam().toHtmlFlag());
      nameSB.append(victim.getName());
    }
    nameSB.append("</html>");
    nameLabel.setText(nameSB.toString());
    descriptionLabel.setText(injury.isHideDescription()?"<<DESCRIPTION HIDDEN>>":injury.getDescription());
  }



  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      nameLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(13f)));
      descriptionLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(7.5f)));
    }

  }

  @Component
  private class InjuryUpdateEventListener implements
      ApplicationListener<InjuryUpdateEvent> {

    @Override
    public void onApplicationEvent(InjuryUpdateEvent injuryUpdateEvent) {
      setInjuryDetails(injuryUpdateEvent.getInjury());
    }

  }

}
