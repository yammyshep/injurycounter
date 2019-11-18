package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
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
    nameLabel.setText(injury.getPerson().getName());
    descriptionLabel.setText(injury.getDescription());
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      nameLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(11.5f)));
      descriptionLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(4.5f)));
    }

  }

}
