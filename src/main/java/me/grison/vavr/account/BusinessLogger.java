package me.grison.vavr.account;

import java.util.UUID;

/**
 * A simple business logger interface.
 *
 * @author Alexandre Grison (a.grison@gmail.com)
 */
public interface BusinessLogger {
    void logSuccessRegister(UUID id);

    void logFailureRegister(UUID id, Throwable exception);
}