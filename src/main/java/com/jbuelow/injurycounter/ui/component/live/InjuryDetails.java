package com.jbuelow.injurycounter.ui.component.live;

import com.jbuelow.injurycounter.data.entity.Injury;
import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

@Component
public class InjuryDetails extends JPanel {

  private final JLabel nameLabel = new JLabel("Joe Smith");
  private final JLabel descriptionLabel = new JLabel("he died");

  @PostConstruct
  public void addComponents() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(nameLabel);
    add(descriptionLabel);
  }

  @PostConstruct
  public void setFonts() {
    nameLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), 45));
    descriptionLabel.setFont(new Font(getFont().getName(), getFont().getStyle(), 25));
  }

  public void setInjuryDetails(Injury injury) {
    nameLabel.setText(injury.getPerson().getName());
    descriptionLabel.setText(injury.getDescription());
  }

}
