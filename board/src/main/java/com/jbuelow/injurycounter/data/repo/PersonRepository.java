package com.jbuelow.injurycounter.data.repo;

import com.jbuelow.injurycounter.data.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
