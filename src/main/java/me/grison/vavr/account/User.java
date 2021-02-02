package me.grison.vavr.account;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * The User class (domain).
 *
 * @author Alexandre Grison (a.grison@gmail.com)
 */
@Data
@Builder
public class User {
    private final UUID id;
    private final String email;
    private final String name;
    private final String password;
}