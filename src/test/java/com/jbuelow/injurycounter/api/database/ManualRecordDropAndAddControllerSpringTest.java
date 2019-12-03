package com.jbuelow.injurycounter.api.database;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestMethodOrder(OrderAnnotation.class)
public class ManualRecordDropAndAddControllerSpringTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  private Long testInjuryId;

  @Before
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  @Order(1)
  public void addPersonTest() throws Exception {
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
  }

  @Test
  @Order(2)
  public void addInjuryTest() throws Exception {
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/api/db/add/injury");
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
  }

  @Test
  @Order(3)
  public void dropInjuryTest() throws Exception {
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/api/db/drop/injury");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"id\": "+testInjuryId+"}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").value(testInjuryId))
        .andDo(print());
  }

  @Test
  @Order(4)
  public void dropPersonTest() throws Exception {
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/api/db/drop/person");
    rb.accept(MediaType.APPLICATION_JSON);
    rb.contentType(MediaType.APPLICATION_JSON);
    rb.content("{\"id\": 1234}");
    mvc.perform(rb)
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$.id").value(1234))
        .andDo(print());
  }


}
