package com.example.bookstore.services;

import com.example.bookstore.models.User;
import org.springframework.stereotype.Service;
import java.util.*;



@Service
public class UserService {
    private Map<String, User> userMap = new HashMap<>();

    public void register(User user) {
        userMap.put(user.getEmail(), user);
        System.out.println("User registered: " + user.getEmail());
        System.out.println("Password (encoded): " + user.getPassword());
    }
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMap.get(email));
    }
}
