package it.academy.attendance.bootstrap;

import it.academy.attendance.configs.BCryptSingleton;
import it.academy.attendance.models.Role;
import it.academy.attendance.models.User;
import it.academy.attendance.repositories.RoleRepository;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role((long) 1, "ADMIN");
        Role roleUser = new Role((long) 2, "USER");

        User user = new User((long)1,
                "w.askhat.serikov@gmail.com",
                BCryptSingleton.getInstance().encode("123"),
                "Askhat", 1,
                new HashSet<Role>(Arrays.asList(roleAdmin, roleUser)));
        userRepository.save(user);

        Role roleTeacher = new Role((long) 3, "TEACHER");
        Role roleStudent = new Role((long) 4, "STUDENT");
        roleRepository.save(roleTeacher);
        roleRepository.save(roleStudent);


    }
}
