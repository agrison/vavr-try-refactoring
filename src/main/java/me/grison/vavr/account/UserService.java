package me.grison.vavr.account;

import io.vavr.collection.Vector;

import java.util.NoSuchElementException;
import java.util.UUID;

public class UserService {
    private final Vector<User> repository = Vector.of(
            User.builder()
                    .id(UUID.fromString("adce9c3b-7b0a-48cd-bf24-dfda278ed634"))
                    .email("keanu.reeves@gmail.com")
                    .name("Keanu Reeves")
                    .password("c;6vk>RWs&Ed]/5*")
                    .build(),
            User.builder()
                    .id(UUID.fromString("872fbf37-0062-4c6d-905b-72411f848dc9"))
                    .email("matthew.mc.conaughey@gmail.com")
                    .name("Matthew McConaughey")
                    .password("wd5!wyY%8L~Ku%7N")
                    .build());

    public User findById(UUID id) throws NoSuchElementException {
        return repository.filter(p -> id.equals(p.getId())).single();
    }

    public void updateTwitterAccountId(UUID id, String twitterAccountId) {
        // do something
    }
}