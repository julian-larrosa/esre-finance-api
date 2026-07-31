package com.esre.user.service;

import com.esre.user.entity.User;

public interface UserService {
    public boolean existsByEmail(String email);
    public User saveUser(User user);

    public User findByEmail(String email);

    public void setPassword(User user, String password);

    public User findByUsername(String username);

    public boolean existsByUsername(String username);
}
