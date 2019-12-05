package com.jbuelow.injurycounter.webui.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import java.util.Objects;
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
import org.springframework.web.util.NestedServletException;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SubmitInjuryControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private InjuryRepository injuryRepo;

  @Autowired
  private PersonRepository personRepo;

  @Autowired
  private AccessLogRepository accessRepo;

  private MockMvc mvc;

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void getFormNoUsersTest() throws Exception {
    log.debug("Fetching submission form page...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/submit");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/submit']").exists())
        .andExpect(xpath("//form/select[@name='person']").exists())
        .andExpect(xpath("//select[@name='instigator']").exists())
        .andExpect(xpath("//form/input[@type='text'][@name='description']").exists())
        .andExpect(xpath("//form/input[@type='submit']").exists())
        .andDo(print());
  }

  @Test
  void getFormWithOneUserTest() throws Exception {
    log.debug("Creating test user...");
    Person person1 = new Person();
    person1.setId(1234L);
    person1.setName("Bumble Doofenson");
    personRepo.save(person1);

    log.debug("Fetching submission form page...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/submit");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/submit']").exists())
        .andExpect(xpath("//form/select[@name='person']").exists())
        .andExpect(xpath("//select[@name='instigator']").exists())
        .andExpect(xpath("//form/input[@type='text'][@name='description']").exists())
        .andExpect(xpath("//form/input[@type='submit']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='1234']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='1234']/p").string("Bumble Doofenson"))
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='1234']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='1234']/p").string("Bumble Doofenson"))
        .andDo(print());

    log.debug("Cleaning up test database...");
    personRepo.deleteAll();
  }

  @Test
  void getFormWithManyUsersTest() throws Exception {
    log.debug("Creating test user 1...");
    Person person1 = new Person();
    person1.setId(1234L);
    person1.setName("Bumble Doofenson");
    personRepo.save(person1);

    log.debug("Creating test user 2...");
    Person person2 = new Person();
    person2.setId(5678L);
    person2.setName("Doofen Bumbleschmirtz");
    personRepo.save(person2);

    log.debug("Creating test user 3...");
    Person person3 = new Person();
    person3.setId(9101L);
    person3.setName("Daddy Devito");
    personRepo.save(person3);

    log.debug("Fetching submission form page...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/submit");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/submit']").exists())
        .andExpect(xpath("//form/select[@name='person']").exists())
        .andExpect(xpath("//select[@name='instigator']").exists())
        .andExpect(xpath("//form/input[@type='text'][@name='description']").exists())
        .andExpect(xpath("//form/input[@type='submit']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='1234']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='1234']/p").string("Bumble Doofenson"))
        .andExpect(xpath("//form/select[@name='person']/option[@value='5678']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='5678']/p").string("Doofen Bumbleschmirtz"))
        .andExpect(xpath("//form/select[@name='person']/option[@value='9101']").exists())
        .andExpect(xpath("//form/select[@name='person']/option[@value='9101']/p").string("Daddy Devito"))
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='1234']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='1234']/p").string("Bumble Doofenson"))
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='5678']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='5678']/p").string("Doofen Bumbleschmirtz"))
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='9101']").exists())
        .andExpect(xpath("//form/select[@name='instigator']/option[@value='9101']/p").string("Daddy Devito"))
        .andDo(print());

    log.debug("Cleaning up test database...");
    personRepo.deleteAll();
  }

  @Test
  void postFormNullTest() throws Exception {
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/submit");
    rb.accept(MediaType.TEXT_HTML);
    rb.contentType("application/x-www-form-urlencoded");

    assertThrows(AssertionError.class, () -> {
      try {
        mvc.perform(rb)
            .andDo(print());
      } catch (NestedServletException e) {
        throw e.getRootCause();
      }
    });
  }

  @Test
  void postFormTest() throws Exception {
    log.debug("Clearing test database...");
    accessRepo.deleteAll();
    injuryRepo.deleteAll();

    log.debug("Creating test user...");
    Person testPerson = new Person();
    testPerson.setId(1234L);
    testPerson.setName("Bumble Doofenson");
    personRepo.save(testPerson);

    log.debug("Posting submission...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/submit");
    rb.accept(MediaType.TEXT_HTML);
    rb.contentType("application/x-www-form-urlencoded");
    rb.content("person=1234&description=Left+the+stove+on+once+again");
    mvc.perform(rb)
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/body").exists())
        .andDo(print());

    log.debug("Checking test database for submitted injury...");
    assertEquals(1, injuryRepo.count());
    assertEquals(1, accessRepo.count());

    Long injuryID = null;
    for (Injury i : injuryRepo.findAll()) {
      assertTrue(Objects.isNull(injuryID));
      assertEquals(testPerson.getId(), i.getPerson().getId());
      assertEquals("Left the stove on once again", i.getDescription());
      injuryID = i.getId();
    }

    boolean firstAL = true;
    for (AccessLog al : accessRepo.findAll()) {
      assertTrue(firstAL);
      assertEquals(injuryID, al.getInjury().getId());
      firstAL = false;
    }

    log.debug("Cleaning up test database...");
    accessRepo.deleteAll();
    injuryRepo.deleteAll();
  }
}