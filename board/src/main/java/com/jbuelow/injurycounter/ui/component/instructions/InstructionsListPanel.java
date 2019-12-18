package com.jbuelow.injurycounter.ui.component.instructions;

import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InstructionsListPanel extends JPanel {

  private final WifiNetworkLabel wifiLabel;
  private final WebAddressLabel webLabel;

  private JLabel paddingLabel = new JLabel("<html><br><br></html>");
  private JLabel smallPaddingLabel = new JLabel("<html><br></html>");

  public InstructionsListPanel(
      WifiNetworkLabel wifiLabel,
      WebAddressLabel webLabel) {
    this.wifiLabel = wifiLabel;
    this.webLabel = webLabel;
  }

  @PostConstruct
  public void init() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    add(paddingLabel);
    add(wifiLabel);
    add(smallPaddingLabel);
    add(webLabel);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      paddingLabel.setFont(new Font(paddingLabel.getFont().getName(), paddingLabel.getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(5f)));
      smallPaddingLabel.setFont(new Font(smallPaddingLabel.getFont().getName(), smallPaddingLabel.getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(5f)));
    }

  }
}
