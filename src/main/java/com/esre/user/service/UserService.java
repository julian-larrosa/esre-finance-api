package com.esre.user.service;

import com.esre.user.entity.User;

public interface UserService {
    public boolean existsByEmail(String email);
    public User saveUser(User user);
    public User findByEmail(String email);
}
