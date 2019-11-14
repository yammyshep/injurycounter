package com.jbuelow.injurycounter.fxui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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
