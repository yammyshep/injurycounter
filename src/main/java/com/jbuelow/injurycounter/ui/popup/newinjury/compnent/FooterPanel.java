package com.jbuelow.injurycounter.ui.popup.newinjury.compnent;

import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.event.injuryupdate.InjuryUpdateEvent;
import com.jbuelow.injurycounter.ui.helper.DisplaySizing;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.apache.commons.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useUI")
public class FooterPanel extends JPanel {

  @Value("${injury-counter.new-injury.footer")
  private String footerText;

  private final JLabel footerLabel = new JLabel("", SwingConstants.CENTER);

  @PostConstruct
  public void init() {
    footerLabel.setText(footerText);
  }

  public void setForInjury(Injury injury) {
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("has/have", Objects.isNull(injury.getInstigator())? "has":"have");
    dataMap.put("prevDays", "[NOT_IMPLEMENTED]");
    dataMap.put("prevTime", "[NOT_IMPLEMENTED]"); //TODO implement this
    footerLabel.setText(StrSubstitutor.replace(footerText, dataMap));
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      DisplaySizing ds = resolutionDeterminedEvent.getSizing();
      footerLabel.setFont(new Font(footerLabel.getFont().getName(), footerLabel.getFont().getStyle(), ds.displayPercent(8)));
    }

  }

  @Component
  private class InjuryUpdateEventListener implements
      ApplicationListener<InjuryUpdateEvent> {

    @Override
    public void onApplicationEvent(InjuryUpdateEvent injuryUpdateEvent) {
      Injury injury = injuryUpdateEvent.getInjury();
      setForInjury(injury);
    }

  }

}
