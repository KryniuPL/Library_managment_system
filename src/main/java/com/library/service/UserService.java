package com.library.service;

import com.library.model.User;

/**
 * Operacje na użytkownikach
 */
public interface UserService {

    User findByUsername(String username);
    public void saveUser(User user);
}
