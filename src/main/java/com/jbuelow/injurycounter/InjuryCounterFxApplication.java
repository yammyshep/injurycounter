package com.jbuelow.injurycounter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class InjuryCounterFxApplication extends Application {

  private ConfigurableApplicationContext context;

  public static void main(String[] args) {
    Application.launch(InjuryCounterFxApplication.class, args);
  }

  @Override
  public void init() throws Exception {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(InjuryCounterFxApplication.class);
    context = builder.run(getParameters().getRaw().toArray(new String[0]));
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Idea Injury Counter");
    primaryStage.setFullScreen(true);
    //primaryStage.setAlwaysOnTop(true);

    Parent root = FXMLLoader.load(new ClassPathResource("fxml/MainUi.fxml").getURL());
    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
