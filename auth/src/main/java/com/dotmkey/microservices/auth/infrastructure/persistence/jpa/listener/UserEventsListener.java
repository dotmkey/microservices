package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.listener;

import com.dotmkey.microservices.auth.domain.model.*;
import lombok.NonNull;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserEventsListener extends AbstractMongoEventListener<User> {
    @Override
    public void onBeforeSave(@NonNull BeforeSaveEvent<User> event) {
        super.onBeforeSave(event);

        var document = event.getDocument();
        var user = event.getSource();

        Assert.notNull(document, "Document must not be null");
        Assert.notNull(user, "User must not e null");

        document.computeIfPresent("accounts", (k, v) -> ((Map<AccountId, Account>) v).values());

        for (Document d : (Collection<Document>) document.get("accounts")) {
            if (d.containsKey("password")) {
                d.put("password_hash", d.get("password", Document.class).get("hash"));
                d.put("password_hash_algorithm", d.get("password", Document.class).get("algorithm"));
                d.remove("password");
            }
        }

        Utils.removeExtraFields(document, Set.of("surrogateId"));
    }

    @Override
    public void onAfterLoad(@NonNull AfterLoadEvent<User> event) {
        super.onAfterLoad(event);

        var document = event.getDocument();
        Assert.notNull(document, "Document must not be null");

        for (Document d : document.getList("accounts", Document.class)) {
            if (d.containsKey("password_hash") && d.containsKey("password_hash_algorithm")) {
                d.put(
                    "password",
                    new Document(
                        Map.of("hash", d.get("password_hash"), "algorithm", d.get("password_hash_algorithm"))));
                d.remove("password_hash");
                d.remove("password_hash_algorithm");
            }
        }

        document.computeIfPresent(
            "accounts",
            (k, v) -> ((List<Document>) v).stream().collect(Collectors.toMap(d -> d.get("_id"), Function.identity()))
        );
    }
}
