package com.synertrade.fi.confinuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB configuration bean
 *
 * @author Andrey Shchagin on 12.02.17.
 *
 */
@Configuration
@EnableMongoRepositories
public class MongoDbConfig extends AbstractMongoConfiguration {
    @Value("${db.name}")
    private String dbName;

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.port}")
    private Integer dbPort;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.synertrade.fi.persistence";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(dbHost, dbPort);
    }

}
