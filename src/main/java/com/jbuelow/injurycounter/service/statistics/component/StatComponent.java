package com.jbuelow.injurycounter.service.statistics.component;

import com.jbuelow.injurycounter.service.statistics.acquisition.AcquisitionThread;
import java.util.List;

public interface StatComponent<Input, Value> {

  AcquisitionThread generateThread();

  Value getValue();

  List<Input> getConsideredData();

  Boolean getNightly();

}
