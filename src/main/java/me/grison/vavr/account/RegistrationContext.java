package me.grison.vavr.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

import java.util.UUID;

@With
@Getter
@AllArgsConstructor
public class RegistrationContext {
    private final UUID id;
    private final String email, name, password;
    private String accountId, token, tweetUrl;

    public RegistrationContext(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
    }
}