package com.dotmkey.microservices.bank.domain.model;

import com.dotmkey.microservices.commons.domain.model.IdentifiedDomainObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Client extends IdentifiedDomainObject {
    private ClientId id;
    private UserId userId;
    private HashMap<BankAccountId, BankAccount> bankAccounts;
    private LocalDateTime since;
    private LocalDateTime blockedAt;
    private LocalDateTime bannedAt;

    public Client(ClientId id, UserId userId, BankAccount.Factory bankAccountFactory) {
        this.setId(id);
        this.setUserId(userId);
        this.addBankAccount(bankAccountFactory);
        this.setSince(LocalDateTime.now());
    }

    public void addBankAccount(BankAccount.Factory bankAccountFactory) {
        var bankAccount = bankAccountFactory.createFor(this);
        this.getBankAccounts().put(bankAccount.getId(), bankAccount);
    }
}
