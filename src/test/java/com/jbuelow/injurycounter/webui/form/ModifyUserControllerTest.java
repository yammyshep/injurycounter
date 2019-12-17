package com.jbuelow.injurycounter.webui.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.entity.Team;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import com.jbuelow.injurycounter.data.repo.TeamRepository;
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
public class ModifyUserControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private PersonRepository personRepo;

  @Autowired
  private TeamRepository teamRepo;

  private MockMvc mvc;

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void getFormNewUserTest() throws Exception {
    log.debug("Fetching blank user form...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/user");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/user']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required]").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly]").doesNotExist())
        .andExpect(xpath("//form//button[@type='submit'][@formmethod='get'][@formnovalidate]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='shortName']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='grade']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender'][@required]").doesNotExist())
        .andExpect(xpath("//form//input[@type='date'][@name='dob']").exists())
        .andExpect(xpath("//form//input[@type='date'][@name='dob'][@required]").doesNotExist())
        .andExpect(xpath("//form//select").exists())
        .andExpect(xpath("//form//select/option[@value='']").exists())
        .andExpect(xpath("//form//input[@type='submit']").exists())
        .andDo(print());
  }

  @Test
  void getFormForExistingUser() throws Exception {
    log.debug("Creating test user...");
    Person p = new Person();
    p.setId(1234L);
    p.setName("Jose Galegrekowsi");
    personRepo.save(p);

    log.debug("Fetching existing user form...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/user");
    rb.param("id", "1234");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/user']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly]").exists())
        .andExpect(xpath("//form//button[@type='submit'][@formmethod='get'][@formnovalidate]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='shortName']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='grade']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender'][@required]").doesNotExist())
        .andExpect(xpath("//form//input[@type='date'][@name='dob']").exists())
        .andExpect(xpath("//form//input[@type='date'][@name='dob'][@required]").doesNotExist())
        .andExpect(xpath("//form//select").exists())
        .andExpect(xpath("//form//select/option[@value='']").exists())
        .andExpect(xpath("//form//input[@type='submit']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly][@value='1234']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required][@value='Jose Galegrekowsi']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='shortName'][@value='"+p.getShortName()+"']").exists())
        .andExpect(xpath("//h2").string("User Found")) //TODO change to match css class
        .andDo(print());

    log.debug("Cleaning up database...");
    personRepo.deleteAll();
  }

  @Test
  void getFormWithTeams() throws Exception {
    log.debug("Creating test teams...");
    Team t1 = new Team();
    t1.setName("First Team");
    Team t2 = new Team();
    t2.setName("Second Team");
    teamRepo.save(t1);
    teamRepo.save(t2);

    log.debug("Fetching user form...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.get("/user");
    rb.accept(MediaType.TEXT_HTML);
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/user']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required]").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly]").doesNotExist())
        .andExpect(xpath("//form//button[@type='submit'][@formmethod='get'][@formnovalidate]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='shortName']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='grade']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender'][@required]").doesNotExist())
        .andExpect(xpath("//form//input[@type='date'][@name='dob']").exists())
        .andExpect(xpath("//form//input[@type='date'][@name='dob'][@required]").doesNotExist())
        .andExpect(xpath("//form//select").exists())
        .andExpect(xpath("//form//select/option[@value='']").exists())
        .andExpect(xpath("//form//select/option[@value='"+t1.getId()+"']").string("First Team"))
        .andExpect(xpath("//form//select/option[@value='"+t2.getId()+"']").string("Second Team"))
        .andExpect(xpath("//form//input[@type='submit']").exists())
        .andDo(print());
  }

  @Test
  void postNewUser() throws Exception {
    log.debug("Posting request to create a new test user...");
    MockHttpServletRequestBuilder rb = MockMvcRequestBuilders.post("/user");
    rb.accept(MediaType.TEXT_HTML);
    rb.contentType("application/x-www-form-urlencoded");
    rb.content("id=1234&name=Jose Galegrekowsi");
    mvc.perform(rb)
        .andExpect(status().is2xxSuccessful())
        .andExpect(xpath("/html").exists())
        .andExpect(xpath("/html/head").exists())
        .andExpect(xpath("/html/body").exists())
        .andExpect(xpath("//form[@method='POST'][@action='/user']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly]").exists())
        .andExpect(xpath("//form//button[@type='submit'][@formmethod='get'][@formnovalidate]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required]").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='shortName']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='grade']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='gender'][@required]").doesNotExist())
        .andExpect(xpath("//form//input[@type='date'][@name='dob']").exists())
        .andExpect(xpath("//form//input[@type='date'][@name='dob'][@required]").doesNotExist())
        .andExpect(xpath("//form//select").exists())
        .andExpect(xpath("//form//select/option[@value='']").exists())
        .andExpect(xpath("//form//input[@type='submit']").exists())
        .andExpect(xpath("//form//input[@type='number'][@name='id'][@required][@readonly][@value='1234']").exists())
        .andExpect(xpath("//form//input[@type='text'][@name='name'][@required][@value='Jose Galegrekowsi']").exists())
        .andExpect(xpath("//h2").string("User Updated")) //TODO change to match css class
        .andDo(print());

    log.debug("Asserting that changes were properly made to the database...");
    assertEquals(1, personRepo.count());

    Person p = personRepo.findById(1234L).orElse(null);
    assertEquals(1234L, p.getId());
    assertEquals("Jose Galegrekowsi", p.getName());
    assertEquals(String.class, p.getShortName().getClass());

    log.debug("Cleaning up database...");
    personRepo.deleteAll();
  }

}
