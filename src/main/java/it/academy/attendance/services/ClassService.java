package it.academy.attendance.services;

import java.util.List;
import java.util.Set;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.User;

public interface ClassService {
    List<Class> getAll();
    Class getById(Long id);
    boolean deleteById(Long id);
    Class save(Class item);
    List<Class> getAllClassesByTeacher(User user);
    Set<User> enrollStudent(Long id, Long userId);
    Set<User> unrollStudent(Long id, Long userId);
}
