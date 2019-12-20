package com.jbuelow.injurycounter.ui.popup.newinjury.compnent;

import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class TitlePanel extends JPanel {

  private final JLabel titleLabel = new JLabel("", SwingConstants.CENTER);
  private final JLabel leftFirework = new JLabel();
  private final JLabel rightFirework = new JLabel();

  @Value("${injury-counter.new-injury.title}")
  private String titleText = "Congratulations!";

  @Value("${injury-counter.new-injury.showFireworks}")
  private Boolean showFireworks;

  private Resource fireworkImageResource;

  @PostConstruct
  public void init() throws IOException {
    titleLabel.setText(titleText);
    if (showFireworks) {
      leftFirework.setIcon(new ImageIcon(fireworkImageResource.getURL()));
      rightFirework.setIcon(new ImageIcon(fireworkImageResource.getURL()));
    }

    setLayout(new BorderLayout());
    add(titleLabel, BorderLayout.CENTER);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      DisplaySizing ds = resolutionDeterminedEvent.getSizing();
      titleLabel.setFont(new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), ds.displayPercent(12)));
    }

  }

}
