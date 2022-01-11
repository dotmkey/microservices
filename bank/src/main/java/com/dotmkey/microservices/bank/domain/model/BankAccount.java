package com.dotmkey.microservices.bank.domain.model;

import com.dotmkey.microservices.commons.domain.model.IdentifiedDomainObject;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class BankAccount extends IdentifiedDomainObject {
    private BankAccountId id;
    private ClientId clientId;
    private String number;
    private boolean isDefault;
    private Plan plan;
    private double amount;
    private LocalDateTime closedAt;

    public enum Plan {
        BASE
    }

    public BankAccount(BankAccountId id, Client client, String number, Plan plan, boolean isDefault) {
        this.setId(id);
        this.setClientId(client.getId());
        this.setNumber(number);
        this.setPlan(plan);
        this.setDefault(isDefault);
    }

    public interface Factory {
        BankAccount createFor(Client client);
    }

    @AllArgsConstructor
    public static class FirstBankAccountFactory implements Factory {
        private final BankAccountId id;
        private final String number;

        @Override
        public BankAccount createFor(Client client) {
            return new BankAccount(this.id, client, this.number, Plan.BASE, true);
        }
    }
}
