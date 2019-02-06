package com.ecommerce.fi.persistence.dao;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by raul on 13/02/17.
 */
@Document(collection = "instance")
public class InstanceDao {

    @Id
    private String instanceId;

    private String type;

    private String accessUrl;

    private String domainUrl;

    private String release;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

    public String getInstanceId() {
        return instanceId;
    }

    public String getType() {
        return type;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public String getRelease() {
        return release;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }
}

