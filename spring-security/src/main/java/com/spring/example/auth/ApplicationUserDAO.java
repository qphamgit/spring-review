package com.spring.example.auth;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<ApplicationUser> findUserByUserName(String username);
}
