package me.grison.vavr.account;

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

    public String register(UUID id) {
        try {
            User user = this.userService.findById(id);

            if (user == null) {
                return null;
            }

            String accountId = this.twitterService.register(user.getEmail(), user.getName());
            if (accountId == null) {
                return null;
            }

            String twitterToken = this.twitterService.authenticate(user.getEmail(), user.getPassword());
            if (twitterToken == null) {
                return null;
            }

            String tweetUrl = this.twitterService.tweet(twitterToken, "Hello I am " + user.getName());
            if (tweetUrl == null) {
                return null;
            }

            this.userService.updateTwitterAccountId(id, accountId);
            businessLogger.logSuccessRegister(id);

            return tweetUrl;
        } catch (Exception ex) {
            this.businessLogger.logFailureRegister(id, ex);
            return null;
        }
    }
}