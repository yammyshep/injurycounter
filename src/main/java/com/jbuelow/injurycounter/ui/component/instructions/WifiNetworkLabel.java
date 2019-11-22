package com.jbuelow.injurycounter.ui.component.instructions;

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
public class WifiNetworkLabel extends JLabel {

  @Value("classpath:ui/icon/wifi-icon.png")
  private Resource icon;

  @Setter
  private String wifiNetworkName = "DC-Guest";

  @PostConstruct
  public void init() throws IOException {
    setText("<html>Connect your phone or laptop to <u>"+wifiNetworkName+"</u></html>");
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
