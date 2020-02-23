package io.github.l0llygag.poscaweb;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    public com.mongodb.client.MongoClient mongoClient() {
        return MongoClients.create(System.getenv("MONGO_URL"));
    }

    @Override
    protected String getDatabaseName() {
        return "poscaweb";
    }
}
