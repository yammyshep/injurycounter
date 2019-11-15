package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.data.entity.Injury;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

}
