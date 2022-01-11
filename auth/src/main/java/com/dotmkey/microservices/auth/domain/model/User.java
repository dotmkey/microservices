package com.dotmkey.microservices.auth.domain.model;

import com.dotmkey.microservices.commons.domain.model.IdentifiedDomainObject;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@AllArgsConstructor(onConstructor_={@PersistenceConstructor}, access = AccessLevel.PRIVATE)
public class User extends IdentifiedDomainObject {
    private UserId id;
    private Map<AccountId, Account> accounts = new HashMap<>();
    private String name;
    private String surname;
    private String lastname;
    private Set<Contact> contacts = new HashSet<>();
    private LocalDateTime registeredAt;
    private LocalDateTime bannedAt;

    public User(UserId id, Account.Factory accountFactory, String name, String surname, String lastname) {
        this.setId(id);
        this.addAccount(accountFactory);
        this.setName(name);
        this.setSurname(surname);
        this.setLastname(lastname);
        this.setRegisteredAt(LocalDateTime.now());
    }

    public void addAccount(Account.Factory accountFactory) {
        var account = accountFactory.createFor(this);
        this.getAccounts().put(account.getId(), account);
    }

    public Optional<Account> getAccountOfId(AccountId accountId) {
        return Optional.of(this.accounts.get(accountId));
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }
}
