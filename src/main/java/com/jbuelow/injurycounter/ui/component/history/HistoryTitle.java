package com.jbuelow.injurycounter.ui.component.history;

import java.awt.Font;
import javax.annotation.PostConstruct;
import javax.swing.JLabel;
import org.springframework.stereotype.Component;

public class HistoryTitle extends JLabel {

  @PostConstruct
  public void init() {
    setText("Injury History");
    setFont(new Font(getFont().getName(), getFont().getStyle(), 80));
  }

}
