package com.jbuelow.injurycounter.fxui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class FxUiController {

  @FXML
  private Label dateTime;

  @FXML
  private Label timeSinceInjury;

  @FXML
  private Label injuredPersonName;

  @FXML
  private Label injuryDescription;

}
