package com.jbuelow.injurycounter.webui;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

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
class IndexControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void showIndexTest() throws Exception {
    log.debug("Fetching index page...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//a[@href='/injury']").exists())
        .andDo(print());
  }
}