package com.jbuelow.injurycounter.data.repo;

import com.jbuelow.injurycounter.data.entity.Injury;
import org.springframework.data.repository.CrudRepository;

public interface InjuryRepository extends CrudRepository<Injury, Long> {

}
