package com.jbuelow.injurycounter.service.update;

import static com.jbuelow.injurycounter.service.update.Sha256Helper.getSHA;
import static com.jbuelow.injurycounter.service.update.Sha256Helper.toHexString;

import com.jbuelow.injurycounter.service.update.config.UpdateConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutomaticUpdateService {

  private final UpdateConfig config;

  private String localJarHash;
  private UpdateState state;
  private File jarLocation;

  public AutomaticUpdateService(
      UpdateConfig config) {
    this.config = config;
  }

  @PostConstruct
  public void hashJar() throws URISyntaxException {
    File f = new ApplicationHome(AutomaticUpdateService.class).getSource();
    jarLocation = f;

    if (f == null) {
      log.error("Jar file not found. Disabling autoupdate service...");
      this.state = UpdateState.DISABLED;
      return;
    }

    if (!f.canRead()) {
      this.state = UpdateState.DISABLED;
      log.error("Error in autoupdater: user does not have read access to jar file. Disabling auto updater");
      return;
    } else if (!f.canWrite()) {
      this.state = UpdateState.NOWRITE;
      log.warn("user does not have write access to jar file. Will still check for updates");
    } else {
      this.state = UpdateState.READY;
      log.debug("Auto updater loaded jar file located at {} successfully", f.getAbsolutePath());
    }

    String jarHash;
    try {
      FileInputStream fis = new FileInputStream(f);
      byte[] jarByteArray = IOUtils.toByteArray(fis);
      byte[] jarHashByteArray = getSHA(jarByteArray);
      jarHash = toHexString(jarHashByteArray);
    } catch (NoSuchAlgorithmException | IOException e) {
      log.error("Disabling autoupdate service to handle exception: ", e);
      this.state = UpdateState.DISABLED;
      return;
    }

    this.localJarHash = jarHash;
    log.info("AutomaticUpdateService has loaded jar hash");
  }

  @Scheduled(fixedRate = 300000)
  public void checkForNewUpdate() throws IOException, InterruptedException {
    if (state != UpdateState.READY) {
      return;
    }

    String downloadedHashFile = new String(IOUtils.toByteArray(new URL(config.getUrl().getHash())));
    String remoteHash = downloadedHashFile.split(" ", 2)[0];

    if (!remoteHash.equals(localJarHash)) {
      log.info("A new update is avaliable.");

      if (state.getLevel() < 0) {
        log.info("Update will not be downloaded as it cannot be written to disk");
        return;
      }

      log.info("Updating software");
      this.state = UpdateState.INPROGRESS;

      log.info("Downloading file: {}", config.getUrl().getJar());
      Files.copy(new URL(config.getUrl().getJar()).openStream(), new File(jarLocation.getPath()+".UPDATE").toPath(),
          StandardCopyOption.REPLACE_EXISTING);
      log.info("Download finished");

      log.warn("\n##################################################\n#  SOFTWARE WILL NOW RESTART TO COMPLETE UPDATE  #\n##################################################");

      Thread.sleep(5000);
      System.exit(0);
    }
  }



}
