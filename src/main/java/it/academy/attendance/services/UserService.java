package it.academy.attendance.services;

import it.academy.attendance.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    boolean deleteById(Long id);
    User save(User item);
    User saveStudent(User item);
    User saveTeacher(User item);
    User getByEmail(String email);
}
