package com.jbuelow.injurycounter.service.statistics.acquisition;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataAcquisitionService {

  private final ExecutorService executorService = Executors.newFixedThreadPool(1);

  @SneakyThrows
  public boolean isWorking() {
    return executorService.awaitTermination(1, TimeUnit.SECONDS);
  }

  public void submitAcquisitions(List<AcquisitionThread> threads) {
    threads.forEach(executorService::submit);
  }
}
