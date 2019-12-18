package com.jbuelow.injurycounter.data.repo;

import com.jbuelow.injurycounter.data.entity.AccessLog;
import org.springframework.data.repository.CrudRepository;

public interface AccessLogRepository extends CrudRepository<AccessLog, Long> {

}
