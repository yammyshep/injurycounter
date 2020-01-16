package com.jbuelow.injurycounter.ui.component.statistics.view;

import com.jbuelow.injurycounter.service.statistics.StatisticsService;
import com.jbuelow.injurycounter.service.statistics.component.impl.NotHiddenInjuriesInDatabaseStat;
import com.jbuelow.injurycounter.ui.component.statistics.StatView;
import com.jbuelow.injurycounter.ui.helper.event.resolutiondetermined.ResolutionDeterminedEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import lombok.Getter;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NumberOfUnhiddenInjuriesStatView extends StatView<NotHiddenInjuriesInDatabaseStat> {

  private final StatisticsService statisticsService;

  private final JLabel value = new JLabel("", SwingConstants.CENTER);

  @Getter
  private final Class<?> classifier = NotHiddenInjuriesInDatabaseStat.class;

  public NumberOfUnhiddenInjuriesStatView(
      StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @PostConstruct
  public void setup() {
    setLayout(new BorderLayout());
    add(value, BorderLayout.CENTER);
  }

  @Override
  public void updateStats() {
    NotHiddenInjuriesInDatabaseStat comp = (NotHiddenInjuriesInDatabaseStat) statisticsService.getStat(getClassifier());
    comp.generateThread().run();
    Double val = comp.getValue();

    String sb = "<html><center>There are<br><b>"
        + val
        + "</b><br>reported injuries on file.</center></html>";
    value.setText(sb);
  }

  @Component
  private class ResolutionDeterminedEventListener implements
      ApplicationListener<ResolutionDeterminedEvent> {

    @Override
    public void onApplicationEvent(ResolutionDeterminedEvent resolutionDeterminedEvent) {
      value.setFont(new Font(getFont().getName(), getFont().getStyle(), resolutionDeterminedEvent.getSizing().displayPercent(12f)));
    }

  }

}
