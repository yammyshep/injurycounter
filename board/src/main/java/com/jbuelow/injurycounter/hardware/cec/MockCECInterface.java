package com.jbuelow.injurycounter.hardware.cec;

import java.io.IOException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!useCEC")
public class MockCECInterface implements CECInterface {

  @Override
  public void makeActiveSource() throws IOException {

  }

  @Override
  public boolean getPowerState(int device) throws IOException {
    return false;
  }

  @Override
  public void setPowerState(int device, boolean state) throws IOException {

  }

}
