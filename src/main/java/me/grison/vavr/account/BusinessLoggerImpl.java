package me.grison.vavr.account;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class BusinessLoggerImpl implements BusinessLogger {
    @Override
    public void logSuccessRegister(UUID id) {
        log.info("We successfully registered the user: {}", id);
    }

    @Override
    public void logFailureRegister(UUID id, Throwable exception) {
        log.error("We failed to register the user: {}", id);
        log.error("Exception", exception);
    }
}