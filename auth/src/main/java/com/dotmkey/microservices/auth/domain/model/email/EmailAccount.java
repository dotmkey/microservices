package com.dotmkey.microservices.auth.domain.model.email;

import com.dotmkey.microservices.auth.domain.DomainRegistry;
import com.dotmkey.microservices.auth.domain.model.Account;
import com.dotmkey.microservices.auth.domain.model.AccountId;
import com.dotmkey.microservices.auth.domain.model.Password;
import com.dotmkey.microservices.auth.domain.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_={@PersistenceConstructor}, access = AccessLevel.PRIVATE)
public class EmailAccount extends Account {
    private String email;
    private Password password;
    private LocalDateTime blockedAt;

    public EmailAccount(AccountId id, User user, String email, String rawPassword) {
        super(id, user);
        this.setEmail(email);
        this.setPassword(DomainRegistry.passwordHasher().hash(rawPassword));
    }

    public Type getType() {
        return Type.EMAIL;
    }

    @AllArgsConstructor
    public static class Factory implements Account.Factory {
        private final AccountId id;
        private final String email;
        private final String rawPassword;

        @Override
        public EmailAccount createFor(User user) {
            return new EmailAccount(this.id, user, this.email, this.rawPassword);
        }
    }
}
