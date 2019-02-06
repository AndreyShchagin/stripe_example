package com.synertrade.fi.service;

import com.synertrade.fi.persistence.dao.StripeSupplierDao;
import com.synertrade.fi.exceptions.GenericFiException;
import com.synertrade.fi.persistence.repositories.SupplierRepositoryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Andrey Shchagin on 03.05.17.
 *
 */
@Service
public class OnBoardingService {
    @Autowired
    private SupplierRepositoryRep supplierRepositoryRep;

    /**
     * Add new supplier to database
     *
     * @param platformSupplierId
     * @param map
     * @throws GenericFiException
     */
    public void addSupplier(String platformSupplierId, Map<String, String> map) throws GenericFiException{
        //TODO: first to check if the supplier ale\reasdy exists - update an account is another business use case
        String apiKey = map.get("access_token");
        String accountId = map.get("stripe_user_id");

        StripeSupplierDao supplierDao;
        try {
            supplierDao = StripeSupplierDao.builder().apiKey(apiKey).stripeAccountId(accountId).platformAccountId(platformSupplierId).build();
        }catch (NullPointerException npe){
            throw new GenericFiException("Not enough parameters to create Supplier DAO", npe);
        }
        supplierRepositoryRep.save(supplierDao);
    }
}
