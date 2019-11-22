package com.jbuelow.injurycounter.ui.component.instructions;

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
public class InstructionsPanel extends JPanel {

  private final InstructionsListPanel listPanel;

  private final JLabel title = new JLabel("Submit your injury", SwingConstants.CENTER);

  public InstructionsPanel(
      InstructionsListPanel listPanel) {
    this.listPanel = listPanel;
  }

  @PostConstruct
  public void init() {
    setLayout(new BorderLayout());
    add(title, BorderLayout.NORTH);
    add(listPanel, BorderLayout.CENTER);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(13f)));
    }

  }

}
