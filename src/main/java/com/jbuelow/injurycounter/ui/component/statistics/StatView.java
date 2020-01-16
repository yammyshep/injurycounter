package com.jbuelow.injurycounter.ui.component.statistics;

import com.jbuelow.injurycounter.service.statistics.component.StatComponent;
import javax.swing.JPanel;

public abstract class StatView<C extends StatComponent<?, ?>> extends JPanel {

  public abstract Class<?> getClassifier();

  public abstract void updateStats();

}
