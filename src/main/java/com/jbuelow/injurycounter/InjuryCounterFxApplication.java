package com.jbuelow.injurycounter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InjuryCounterFxApplication extends Application {

  private ConfigurableApplicationContext context;
  private FXMLLoader loader;

  private Parent root;

  public static void main(String[] args) {
    Application.launch(InjuryCounterFxApplication.class, args);
  }

  @Override
  public void init() throws Exception {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(InjuryCounterFxApplication.class);
    context = builder.run(getParameters().getRaw().toArray(new String[0]));
    loader = new FXMLLoader();
    loader.setControllerFactory(context::getBean);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    loader.setLocation(getClass().getResource("fxml/MainUi.fxml"));
    root = loader.load();

    primaryStage.setTitle("Idea Injury Counter");
    primaryStage.setFullScreen(true);
    primaryStage.setAlwaysOnTop(true);

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void stop() {
    context.stop();
  }
}
