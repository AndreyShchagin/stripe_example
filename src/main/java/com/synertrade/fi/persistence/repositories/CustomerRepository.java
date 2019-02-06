package com.synertrade.fi.persistence.repositories;

import com.synertrade.fi.persistence.dao.InstanceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Stores information about platforms to be integrated
 *
 * @author Andrey Shchagin on 03.05.17.
 */
public interface CustomerRepository extends MongoRepository<InstanceDao, String> {
    @Query("{'platformAccountId' : ?0}")
    InstanceDao getByInstanceId(String instanceId);
}

