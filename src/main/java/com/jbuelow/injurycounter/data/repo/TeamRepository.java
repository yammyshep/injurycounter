package com.jbuelow.injurycounter.data.repo;

import com.jbuelow.injurycounter.data.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
