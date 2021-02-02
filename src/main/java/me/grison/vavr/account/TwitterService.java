package me.grison.vavr.account;

/**
 * Fake Twitter Service returning plain strings for the purpose of this example.
 *
 * @author Alexandre Grison (a.grison@gmail.com)
 */
public class TwitterService {
    public String register(String email, String name) {
        return "TwitterAccountId";
    }

    public String authenticate(String email, String password) {
        return "ATwitterToken";
    }

    public String tweet(String token, String message) {
        return "TweetUrl";
    }
}