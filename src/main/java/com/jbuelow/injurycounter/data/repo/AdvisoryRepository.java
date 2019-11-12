package com.jbuelow.injurycounter.data.repo;

import com.jbuelow.injurycounter.data.entity.Advisory;
import org.springframework.data.repository.CrudRepository;

public interface AdvisoryRepository extends CrudRepository<Advisory, Long> {

}
