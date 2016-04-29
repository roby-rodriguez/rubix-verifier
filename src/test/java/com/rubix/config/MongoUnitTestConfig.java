package com.rubix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

@Profile(value = {"test", "unit" })
@Configuration
public class MongoUnitTestConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "rubix";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Fongo(getDatabaseName()).getMongo();
    }
}
