package com.jbuelow.injurycounter.api.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ManualRecordDropAndAddControllerMethodTest {

  @InjectMocks
  private DropRecordController dropController;

  @InjectMocks
  private ManualAddController addController;

  @Spy
  private PersonRepository personRepository;

  @Spy
  private InjuryRepository injuryRepository;

  @Spy
  private AccessLogRepository accessLogRepository;

  private long injuryId;

  @Test
  @Order(1)
  void addPerson() {
    MockHttpServletRequest addRequest = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(addRequest));

    Person addInput = new Person();
    addInput.setId(1234567890L);
    addInput.setName("Bumble Doofenson");

    Person addOutput = addController.addPerson(addRequest, addInput);

    verify(personRepository).save(any(Person.class));
    verify(accessLogRepository).save(any(AccessLog.class));

    assertEquals(new Long(1234567890L), addOutput.getId());
    assertEquals("Bumble Doofenson", addOutput.getName());
    assertEquals("Bumble D.", addOutput.getShortName());
  }

  @Test
  @Order(2)
  void addInjury() {
    MockHttpServletRequest addRequest = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(addRequest));

    Person person = new Person();
    person.setId(1234567890L);
    Injury addInjury = new Injury();
    addInjury.setId(1L);
    addInjury.setPerson(person);

    Injury addOutput = addController.addInjury(addRequest, addInjury);

    verify(injuryRepository).save(any(Injury.class));
    verify(accessLogRepository).save(any(AccessLog.class));

    assertNotNull(addOutput.getId());
    injuryId = addOutput.getId();
    assertEquals(new Long(1234567890L), addOutput.getPerson().getId());
  }

  @Test
  @Order(3)
  void dropInjury() {
    MockHttpServletRequest dropRequest = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(dropRequest));

    Injury inputInjury = new Injury();
    inputInjury.setId(injuryId);

    Injury output = dropController.dropInjury(dropRequest, inputInjury);

    verify(injuryRepository).delete(any(Injury.class));
    verify(accessLogRepository).findAll();

    assertEquals(new Long(injuryId), output.getId());
  }

  @Test
  @Order(4)
  void dropPerson() {
    MockHttpServletRequest dropRequest = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(dropRequest));

    Person dropInput = new Person();
    dropInput.setId(1234567890L);

    Person dropOutput = dropController.dropPerson(dropRequest, dropInput);

    verify(personRepository).delete(any(Person.class));
    verify(accessLogRepository).findAll();

    assertEquals(new Long(1234567890L), dropOutput.getId());
  }
}