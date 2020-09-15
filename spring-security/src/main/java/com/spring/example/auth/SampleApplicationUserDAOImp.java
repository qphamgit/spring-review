package com.spring.example.auth;

import com.google.common.collect.Lists;
import com.spring.example.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spring.example.security.ApplicationUserRole.*;

@Repository("sample")
public class SampleApplicationUserDAOImp implements ApplicationUserDAO{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> findUserByUserName(String username) {
        return getAllUsers().stream().filter( user -> username.equals(user.getUsername())).findFirst();
    }

    private List<ApplicationUser> getAllUsers() {
        return Lists.newArrayList(
            new ApplicationUser("student",
                    passwordEncoder.encode("password"),
                    STUDENT.getAuthorities(),
                    true,
                    true,
                    true,
                    true),
                new ApplicationUser("admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser("trainee",
                        passwordEncoder.encode("password"),
                        ADMIN_TRAINEE.getAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
    }
}
