package com.jbuelow.injurycounter.fxui;

import com.jbuelow.injurycounter.data.entity.Injury;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class FxUiController {

  @FXML
  @Getter
  private Label dateTime;

  @FXML
  @Getter
  private Label timeSinceInjury;

  @FXML
  private Label injuredPersonName;

  @FXML
  private Label injuryDescription;

  public void updateInjury(Injury newInjury) {
    injuredPersonName.setText(newInjury.getPerson().getName());
    injuryDescription.setText(newInjury.getDescription());
  }

}
