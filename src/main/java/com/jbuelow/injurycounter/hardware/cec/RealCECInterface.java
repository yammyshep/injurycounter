package com.jbuelow.injurycounter.hardware.cec;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useCEC")
public class RealCECInterface implements CECInterface {

  private Process client;

  @PostConstruct
  public void init() throws IOException {
    client = Runtime.getRuntime().exec("cec-client -d 1");
  }

  @Override
  public void makeActiveSource() throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
    writer.write("as\n");
    writer.flush();
    writer.close();
  }

  @Override
  @Deprecated //until working
  public boolean getPowerState(int device) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
    InputStreamReader reader = new InputStreamReader(client.getInputStream());
    writer.write("pow "+device+"\n");
    writer.flush();

    writer.close();
    reader.close();
    return false;
  }

  @Override
  public void setPowerState(int device, boolean state) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
    writer.write((state?"on ":"standby ")+device+"\n");
    writer.flush();
    writer.close();
  }

}
