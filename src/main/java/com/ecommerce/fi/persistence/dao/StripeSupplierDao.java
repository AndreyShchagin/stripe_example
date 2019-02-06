package com.ecommerce.fi.persistence.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Supplier DAO
 *
 * @author Andrey Shchagin on 03.05.17.
 *
 */
@Builder
@Document(collection = "stripeSuppliers")
public class StripeSupplierDao {
    @Id
    private String stripeAccountId;
    private String platformAccountId;
    private String apiKey;


    public String getStripeAccountId() {
        return stripeAccountId;
    }

    public void setStripeAccountId(String stripeAccountId) {
        this.stripeAccountId = stripeAccountId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Account ID in supplier database
     * @return
     */
    public String getplatformAccountId() {
        return platformAccountId;
    }

    public void setplatformAccountId(String platformAccountId) {
        this.platformAccountId = platformAccountId;
    }
}
