package it.academy.attendance.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptSingleton {
    private static BCryptPasswordEncoder ourInstance = new BCryptPasswordEncoder();

    public static BCryptPasswordEncoder getInstance() {
        return ourInstance;
    }

    private BCryptSingleton() {
    }
}
