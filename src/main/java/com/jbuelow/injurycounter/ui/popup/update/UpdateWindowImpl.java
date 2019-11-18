package com.jbuelow.injurycounter.ui.popup.update;

import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Profile("useUI")
public class UpdateWindowImpl extends JDialog implements UpdateWindow {

  private JLabel message = new JLabel("Downloading update...");

  @Value("classpath:ui/icon/update-icon.gif")
  private Resource updateIcon;

  @PostConstruct
  public void init() throws IOException {
    setTitle("Software Update");
    setAlwaysOnTop(true);
    setResizable(false);

    message.setIcon(new ImageIcon(updateIcon.getURL()));
    setLayout(new BorderLayout());
    add(message, BorderLayout.CENTER);
  }

  @Override
  public void setVisibiltiy(boolean visibiltiy) {
    setVisible(visibiltiy);
  }

  @Override
  public void setText(String text) {
    message.setText(text);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      DisplaySizing ds = resolutionDeterminedEvent.getSizing();
      setSize(ds.displayPercent(80), ds.displayPercent(30));
      setLocationRelativeTo(null);
      message.setFont(new Font(message.getFont().getName(), message.getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(5f)));
    }

  }
}
