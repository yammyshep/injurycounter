package com.jbuelow.injurycounter.hardware.cec;

import java.io.IOException;

public interface CECInterface {

  void makeActiveSource() throws IOException;

  @Deprecated //until working
  boolean getPowerState(int device) throws IOException;

  void setPowerState(int device, boolean state) throws IOException;

}
