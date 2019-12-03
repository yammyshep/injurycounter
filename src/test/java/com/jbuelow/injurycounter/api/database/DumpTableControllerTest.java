package com.jbuelow.injurycounter.api.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import com.jbuelow.injurycounter.data.entity.Injury;
import com.jbuelow.injurycounter.data.entity.Person;
import com.jbuelow.injurycounter.data.repo.AccessLogRepository;
import com.jbuelow.injurycounter.data.repo.InjuryRepository;
import com.jbuelow.injurycounter.data.repo.PersonRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DumpTableControllerTest {

  @InjectMocks
  private DumpTableController controller;

  @Mock
  private InjuryRepository injuryRepo;

  @Mock
  private PersonRepository personRepo;

  @Mock
  private AccessLogRepository accessLogRepo;

  @Test
  void dumpInjuries() {
    Iterable<Injury> injuries = controller.dumpInjuries();

    assertEquals(ArrayList.class, injuries.getClass());
  }

  @Test
  void dumpPersons() {
    Iterable<Person> persons = controller.dumpPersons();

    assertEquals(ArrayList.class, persons.getClass());
  }

  @Test
  void dumpAccessLog() {
    Iterable<AccessLog> accessLogs = controller.dumpAccessLog();

    assertEquals(ArrayList.class, accessLogs.getClass());
  }
}