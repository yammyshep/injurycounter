package com.jbuelow.injurycounter.api.power;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class DisplayPowerControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void powerOnTest() throws Exception {
    log.debug("Trying power on API endpoint...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/api/power/on");
    rb.accept(MediaType.ALL);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
  }

  @Test
  void powerOffTest() throws Exception {
    log.debug("Trying power off API endpoint...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/api/power/off");
    rb.accept(MediaType.ALL);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
  }
}