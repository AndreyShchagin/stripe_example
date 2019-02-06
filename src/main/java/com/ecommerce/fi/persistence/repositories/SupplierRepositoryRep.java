package com.ecommerce.fi.persistence.repositories;

import com.ecommerce.fi.persistence.dao.StripeSupplierDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Supplier Mongo Repo
 * @author Andrey Shchagin on 03.05.17.
 *
 */
public interface SupplierRepositoryRep extends MongoRepository<StripeSupplierDao, String> {
    @Query("{'platformAccountId' : ?0}")
    StripeSupplierDao findByNativeSupplierId(String nativeSuppId);
}
