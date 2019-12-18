package com.jbuelow.injurycounter.hardware.cec;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("useCEC")
public class RealCECInterface implements CECInterface {

  private Process client;

  private OutputStreamWriter out;
  private InputStreamReader in;

  @PostConstruct
  public void init() throws IOException {
    client = Runtime.getRuntime().exec("cec-client -d 1");
    out = new OutputStreamWriter(client.getOutputStream());
    in = new InputStreamReader(client.getInputStream());
  }

  @Override
  public void makeActiveSource() throws IOException {
    sendCommand("as");
  }

  @Override
  @Deprecated //until working
  public boolean getPowerState(int device) throws IOException {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public void setPowerState(int device, boolean state) throws IOException {
    sendCommand((state?"on ":"standby ")+device);
  }

  private void sendCommand(String command) {
    assert client.isAlive() : "Client is dead";
    try {
      log.debug("Writing command to cecClient: '{}'", command);
      out.write(command+"\n");
      out.flush();
    } catch (IOException e) {
      log.error("Exception:", e);
    }
  }

}
