package it.academy.attendance.helpers;

import it.academy.attendance.models.User;
import it.academy.attendance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
    private final UserService userService;
    public Helper(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.getByEmail(userDetails.getUsername());
    }
}
