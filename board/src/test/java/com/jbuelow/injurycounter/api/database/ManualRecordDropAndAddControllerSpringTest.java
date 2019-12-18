package com.jbuelow.injurycounter.api.database;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.jayway.jsonpath.JsonPath;
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
public class ManualRecordDropAndAddControllerSpringTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  private Long testInjuryId;

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void addThenDropPersonInjuryTest() throws Exception {
    log.debug("Adding person to database...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/api/db/add/person");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"id\": 1234, \"name\": \"Bumble Doofenson\"}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").value(1234))
        .andExpect(jsonPath("$.name").value("Bumble Doofenson"))
        .andExpect(jsonPath("$.shortName").value("Bumble D."))
        .andDo(print());

    log.debug("Adding injury to database...");
    rb = MockMvcRequestBuilders.post("/api/db/add/injury");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"person\": {\"id\": 1234}, \"description\": \"Left the stove on\"}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.description").value("Left the stove on"))
        .andExpect(jsonPath("$.person").exists())
        .andExpect(jsonPath("$.person.id").value(1234))
        .andDo(mvcResult -> testInjuryId = new Long((Integer)JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id")))
        .andDo(print());

    log.debug("Dropping injury from database...");
    rb = MockMvcRequestBuilders.post("/api/db/drop/injury");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"id\": "+testInjuryId.toString()+"}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").value(testInjuryId))
        .andDo(print());

    log.debug("Dropping person from database...");
    rb = MockMvcRequestBuilders.post("/api/db/drop/person");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"id\": 1234}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").value(1234))
        .andDo(print());
  }


}
