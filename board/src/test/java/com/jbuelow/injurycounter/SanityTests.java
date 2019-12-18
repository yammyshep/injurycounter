package com.jbuelow.injurycounter;


import static org.junit.Assert.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SanityTests {

  @Test
  void assureConstantsOfOurUniverseHaveNotBeenBrokenAlongWithSpacetimeItself() {
    assertEquals(1, 1);
    assertEquals(1 + 1, 2);
    assertEquals(5 - 2, 3);
    assertEquals(5 * 2, 10);
    assertEquals(8 / 2, 4);
    log.debug("Our universe is working properly.");
  }

}
