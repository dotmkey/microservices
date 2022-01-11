package com.dotmkey.microservices.auth.infrastructure.persistence.jpa.listener;

import org.bson.Document;

import java.util.Collection;
import java.util.Set;

public class Utils {
    public static void removeExtraFields(Document document, Set<String> extraFields) {
        for (var iterator = document.entrySet().iterator(); iterator.hasNext(); ) {
            var entry = iterator.next();

            if (extraFields.contains(entry.getKey())) {
                iterator.remove();
            }

            if (entry.getValue() instanceof Document) {
                removeExtraFields((Document) entry.getValue(), extraFields);
            }

            if (entry.getValue() instanceof Collection) {
                for (Object o : (Collection<?>) entry.getValue()) {
                    if (o instanceof Document) {
                        removeExtraFields((Document) o, extraFields);
                    }
                }
            }
        }
    }
}
