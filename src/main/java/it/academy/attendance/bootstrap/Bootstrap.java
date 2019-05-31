package it.academy.attendance.bootstrap;

import it.academy.attendance.configs.BCryptSingleton;
import it.academy.attendance.models.Class;
import it.academy.attendance.models.Lecture;
import it.academy.attendance.models.Role;
import it.academy.attendance.models.User;
import it.academy.attendance.repositories.ClassRepository;
import it.academy.attendance.repositories.LectureRepository;
import it.academy.attendance.repositories.RoleRepository;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private LectureRepository lectureRepository;


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


        User teacher = new User((long)2,
                "teacher@gmail.com",
                BCryptSingleton.getInstance().encode("123"),
                "Teacher", 1,
                new HashSet<Role>(Arrays.asList(roleTeacher)));
        userRepository.save(teacher);

        User student = new User((long)3,
                "student@gmail.com",
                BCryptSingleton.getInstance().encode("123"),
                "Student", 1,
                new HashSet<Role>(Arrays.asList(roleStudent)));
        userRepository.save(student);

        Lecture lecture = new Lecture(LocalDateTime.now());
        lectureRepository.save(lecture);
        Class cl = new Class((long) 1,
                "Java",
                teacher,
                new HashSet<>(Arrays.asList(student)),
                new HashSet<>(Arrays.asList(lecture)));
        classRepository.save(cl);

    }
}
