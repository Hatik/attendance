package it.academy.attendance.services;

import it.academy.attendance.configs.BCryptSingleton;
import it.academy.attendance.models.Role;
import it.academy.attendance.models.User;
import it.academy.attendance.repositories.RoleRepository;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            // TODO write to log object
            return false;
        }
    }

    @Override
    public User saveStudent(User item) {
        item.setRoles(getRoleSetFromSingleInstance(getRoleByString("STUDENT")));
        return save(item);
    }

    @Override
    public User saveTeacher(User item) {
        item.setRoles(getRoleSetFromSingleInstance(getRoleByString("TEACHER")));
        return save(item);
    }

    @Override
    public User save(User item) {
        if (item.getId() == null || !userRepository.existsById(item.getId())) {// Register
            item.setPassword(BCryptSingleton.getInstance().encode(item.getPassword()));
        }
        return userRepository.save(item);
    }

    private Role getRoleByString(String roleName) {
        return roleRepository.findAll().stream().filter(x -> x.getName().equals(roleName)).findFirst().get();
    }
    private HashSet<Role> getRoleSetFromSingleInstance(Role role){
        return new HashSet<>(Arrays.asList(role));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findFirstByEmail(email);
//        return userRepository.findAll().stream().filter(x -> x.getEmail().equals(email)).findFirst().get();

    }
}
