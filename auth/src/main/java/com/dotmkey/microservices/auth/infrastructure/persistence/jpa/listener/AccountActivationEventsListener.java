package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.listener;

import com.dotmkey.microservices.auth.domain.model.AccountActivation;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;

@Component
public class AccountActivationEventsListener<T extends AccountActivation> extends AbstractMongoEventListener<T> {
    @Override
    public void onBeforeSave(@NonNull BeforeSaveEvent<T> event) {
        super.onBeforeSave(event);

        var document = event.getDocument();
        Assert.notNull(document, "Document must not be null");

        Utils.removeExtraFields(document, Set.of("surrogateId"));
    }
}
