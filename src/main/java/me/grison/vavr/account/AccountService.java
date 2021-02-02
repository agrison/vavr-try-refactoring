package me.grison.vavr.account;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;

import java.util.UUID;

/**
 * Account Service whose job is to provide a `register` method which let the caller
 * register a user on a fake twitter based on its ID (UUID).
 *
 * @author Alexandre Grison (a.grison@gmail.com)
 */
@AllArgsConstructor
public class AccountService {

    private final UserService userService;
    private final TwitterService twitterService;
    private final BusinessLogger businessLogger;

    private Try<RegistrationContext> createContext(UUID userId) {
        return Try.of(() -> userService.findById(userId)
                .getOrElseThrow(() -> new IllegalStateException("No user to register")))
                .map(RegistrationContext::new);
    }

    private Try<RegistrationContext> registerOnTwitter(RegistrationContext context) {
        return Try.of(() -> twitterService.register(context.getEmail(), context.getName()))
                .map(context::withAccountId);
    }

    private Try<RegistrationContext> authenticateOnTwitter(RegistrationContext context) {
        return Try.of(() -> twitterService.authenticate(context.getEmail(), context.getPassword()))
                .map(context::withToken);
    }

    private Try<RegistrationContext> tweet(RegistrationContext context) {
        return Try.of(() -> twitterService.tweet(context.getToken(), "Hello I am " + context.getName()))
                .map(context::withTweetUrl);
    }

    private void updateUser(RegistrationContext context) {
        Try.run(() -> userService.updateTwitterAccountId(context.getId(), context.getAccountId()));
    }


    public Option<String> register(UUID id) {
        return createContext(id)
                .flatMap(this::registerOnTwitter)
                .flatMap(this::authenticateOnTwitter)
                .flatMap(this::tweet)
                .andThen(this::updateUser)
                .andThen(context -> businessLogger.logSuccessRegister(context.getId()))
                .onFailure(exception -> businessLogger.logFailureRegister(id, exception))
                .map(RegistrationContext::getTweetUrl)
                .toOption();
    }
}
