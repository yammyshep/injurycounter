package com.jbuelow.injurycounter.ui.popup.update;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!useUI")
public class UpdateWindowMockImpl implements UpdateWindow {

  @Override
  public void setVisibiltiy(boolean visibiltiy) {

  }

  @Override
  public void setText(String text) {

  }
}
