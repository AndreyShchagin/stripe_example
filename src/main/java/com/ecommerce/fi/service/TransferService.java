package com.ecommerce.fi.service;

import com.ecommerce.fi.persistence.dao.StripeSupplierDao;
import com.ecommerce.fi.persistence.repositories.SupplierRepositoryRep;
import com.stripe.exception.*;
import com.stripe.model.Transfer;
import com.stripe.net.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrey Shchagin on 27.04.17.
 *
 */
@Service
public class TransferService {

    @Autowired
    private SupplierRepositoryRep supplierRepo;

    /**
     * Make a transfer to registered account
     *
     * @param amount - transfer amount
     * @param currency - transfer currency (must match supplier account currency)
     * @param supplierId - platform supplier ID (the one from supplier database)
     * @param invoiceNo - Invoice ID from invoicing app
     * @return
     * @throws CardException
     * @throws APIException
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     */
    public Transfer transferToSubAccount(BigDecimal amount, String currency, String supplierId, String invoiceNo) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        StripeSupplierDao stripeSupplier = getSupplierAccountById(supplierId);
        Map<String, Object> transferParams = new HashMap<>();
        transferParams.put("amount", amount);
        transferParams.put("currency", currency);

        transferParams.put("destination", stripeSupplier.getStripeAccountId());
        transferParams.put("transfer_group", invoiceNo);

        //"sk_test_IxFp9NxbzvH8LNfxIl7egue8"
        return Transfer.create(transferParams, new RequestOptions.RequestOptionsBuilder().setApiKey(stripeSupplier.getApiKey()).build());
        //Make monet transfer to managed account

    }


    private StripeSupplierDao getSupplierAccountById(String supplierId) {
        return supplierRepo.findByNativeSupplierId(supplierId);
    }
}
