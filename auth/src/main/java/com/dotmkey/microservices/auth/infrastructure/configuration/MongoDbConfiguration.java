package com.dotmkey.microservices.auth.infrastructure.configuration;

import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccount;
import com.dotmkey.microservices.auth.domain.model.email.EmailAccountActivation;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccount;
import com.dotmkey.microservices.auth.domain.model.phone.PhoneAccountActivation;
import com.dotmkey.microservices.auth.infrastructure.persistence.jpa.converter.*;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ConfigurableTypeInformationMapper;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;
import java.util.Map;

@Configuration
public class MongoDbConfiguration extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Override
    protected @NonNull String getDatabaseName() {
        return "auth";
    }

    @Override
    protected void configureClientSettings(@NonNull MongoClientSettings.Builder builder) {
        super.configureClientSettings(builder);
        builder.applicationName("bank").applyConnectionString(new ConnectionString(this.uri));
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(new UserIdReadConverter());
        adapter.registerConverter(new UserIdWriteConverter());
        adapter.registerConverter(new AccountIdReadConverter());
        adapter.registerConverter(new AccountIdWriteConverter());
        adapter.registerConverter(new AccountActivationIdReadConverter());
        adapter.registerConverter(new AccountActivationIdWriteConverter());
    }

    @Override
    public @NonNull MongoTemplate mongoTemplate(
            @NonNull MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
        converter.setTypeMapper(new DefaultMongoTypeMapper(
            "_type",
            List.of(
                new ConfigurableTypeInformationMapper(Map.of(
                    EmailAccountActivation.class, "AccountActivation#" + Account.Type.EMAIL.name(),
                    PhoneAccountActivation.class, "AccountActivation#" + Account.Type.PHONE.name()
                )),
                new ConfigurableTypeInformationMapper(Map.of(
                    EmailAccount.class, "Account#" + Account.Type.EMAIL.name(),
                    PhoneAccount.class, "Account#" + Account.Type.PHONE.name()
                ))
            )
        ));
        return super.mongoTemplate(databaseFactory, converter);
    }
}
