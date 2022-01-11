package com.dotmkey.microservices.auth.domain.model.phone;

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
public class PhoneAccount extends Account {
    private String phone;
    private Password password;
    private LocalDateTime blockedAt;

    public PhoneAccount(AccountId id, User user, String phone, String rawPassword) {
        super(id, user);
        this.setPhone(phone);
        this.setPassword(DomainRegistry.passwordHasher().hash(rawPassword));
    }

    public Type getType() {
        return Type.PHONE;
    }

    @AllArgsConstructor
    public static class Factory implements Account.Factory {
        private final AccountId id;
        private final String phone;
        private final String rawPassword;

        @Override
        public PhoneAccount createFor(User user) {
            return new PhoneAccount(this.id, user, this.phone, this.rawPassword);
        }
    }
}
