package com.jbuelow.injurycounter.ui.component.statistics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatisticsPanel extends JPanel implements ApplicationContextAware {

  @Setter
  private ApplicationContext applicationContext;

  private final JTabbedPane tabbedPane = new JTabbedPane();

  private final Random r = new Random();

  @PostConstruct
  public void setup() {
    tabbedPane.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI(){
      @Override
      protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
        return 0;
      }
      protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}
    });
    add(tabbedPane, BorderLayout.CENTER);
    tabbedPane.removeAll();
    log.debug("Starting statistic view registration");
    applicationContext.getBeansOfType(StatView.class).forEach((key, value) -> {
      log.debug("Found StatView: {}", value.getClass().getCanonicalName());
      tabbedPane.addTab(key, value);
    });
    log.info("Successfully loaded all statistic views!");
  }

  public void selectRandomView() {
    tabbedPane.setSelectedIndex(r.nextInt(tabbedPane.getTabCount()));
    ((StatView<?>)tabbedPane.getSelectedComponent()).updateStats();
  }

}
