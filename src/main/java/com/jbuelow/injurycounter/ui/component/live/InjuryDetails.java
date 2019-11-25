package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
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

  public void setInjuryDetails(Injury injury) {
    StringBuilder nameSB = new StringBuilder();
    Person instigator = injury.getInstigator();
    Person victim = injury.getPerson();
    if (instigator != null &&
        instigator != victim) {
      nameSB.append(Optional.ofNullable(instigator.getShortName()).orElse(instigator.getName()));
      nameSB.append(" ")
          .append(nameLabel.getFont().canDisplay('\u2192') ? "\u2192" : ">")
          .append(" ");
    }
    nameSB.append(victim.getName());
    nameLabel.setText(nameSB.toString());
    descriptionLabel.setText(injury.getDescription());
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

}
