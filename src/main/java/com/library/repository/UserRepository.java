package com.library.repository;

import com.library.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers(long max,int count);

    User save(User user);

    User findByUsername(String username);
}
