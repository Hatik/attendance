package it.academy.attendance.controllers;

import it.academy.attendance.models.Role;
import it.academy.attendance.models.User;
import it.academy.attendance.repositories.RoleRepository;
import it.academy.attendance.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAll();
    }
    @GetMapping("/info")
    public String getInfo(){
        Role role = roleRepository.findFirstByName("TEACHER");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("User has authorities: " + userDetails.getAuthorities());
            return userDetails.getAuthorities().toString();
        }
        return "Error";
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            //TODO logger
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }
    @PostMapping("/register")
    public ResponseEntity registerStudent(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.saveStudent(user), HttpStatus.CREATED);
        } catch (Exception e) {
            //TODO logger
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            //TODO logger
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "");
        }
    }
}
