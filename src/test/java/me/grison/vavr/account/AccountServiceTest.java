package me.grison.vavr.account;

import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * In this real life example you will have to combine what you have learned on vavr
 * Refactor the AccountService, write cleaner code by using vavr
 * You will use at least :
 * <p/>
 * {@link Option#getOrElse(Object)}<br>
 * {@link Try#map(Function)}<br>
 * {@link Try#flatMap(Function)}<br>
 * {@link Try#onSuccess(Consumer)}<br>
 * {@link Try#onFailure(Consumer)}<br>
 */
public class AccountServiceTest {
    private final UUID KEANU = UUID.fromString("adce9c3b-7b0a-48cd-bf24-dfda278ed634");
    private final UUID UNKNOWN_USER = UUID.fromString("8c82505d-6d18-4fb7-8b1d-41d88c5bfdf9");

    private AccountService accountService;

    @Before
    public void setup() {
        accountService = new AccountService(
                new UserService(),
                new TwitterService(),
                new BusinessLoggerImpl());
    }

    @Test
    public void registering_Keanu_Reeves_should_return_a_new_tweet_url() {
        String tweetUrl = accountService.register(KEANU);
        Assert.assertEquals("TweetUrl", tweetUrl);
    }

    @Test
    public void register_an_unknown_user_should_return_an_error_message() {
        String tweetUrl = accountService.register(UNKNOWN_USER);
        Assert.assertNull(tweetUrl);
    }
}