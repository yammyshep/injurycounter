package com.jbuelow.injurycounter.ui.component.instructions;

import com.jbuelow.injurycounter.ui.component.instructions.config.InstructionsPanelConfig;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.Font;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class WebAddressLabel extends JLabel {

  @Value("classpath:ui/icon/web-icon.png")
  private Resource icon;

  @Setter
  private String webAddress;

  public WebAddressLabel(
      InstructionsPanelConfig config) {
    this.webAddress = config.getWebAddress();
  }

  @PostConstruct
  public void init() throws IOException {
    setText("<html>Open a web browser and go to <u>"+ webAddress+"</u></html>");
    setIconTextGap(25);
    setIcon(new ImageIcon(icon.getURL()));
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(5f)));
    }

  }

}
