package it.academy.attendance.services;

import it.academy.attendance.models.User;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImpl implements Service<User> {
    @Autowired
    private UserRepository userRepository;
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
    public User save(User item) {
        return userRepository.save(item);
    }
}
