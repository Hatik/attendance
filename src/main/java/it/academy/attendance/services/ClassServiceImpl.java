package it.academy.attendance.services;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.User;
import it.academy.attendance.repositories.ClassRepository;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

    @Override
    public Class getById(Long id) {
        return classRepository.findById(id).get();
    }

    @Override
    public Class save(Class item) {
        return classRepository.save(item);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            //TODO delete only if the current user is the teacher, or is Admin
            classRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            // TODO write to log object
            return false;
        }
    }

    @Override
    public List<Class> getAllClassesByTeacher(User user) {
        return classRepository.getAllByTeacher(user);
    }

    @Override
    public Set<User> enrollStudent(Long id, Long userId) {
        User user = userRepository.findById(userId).get();
        Class classObj = classRepository.findById(id).get();
        Set<User> set = classObj.getStudents();
        set.add(user);
        classRepository.save(classObj);
        return set;
    }

    @Override
    public Set<User> unrollStudent(Long id, Long userId) {
        User user = userRepository.findById(userId).get();
        Class classObj = classRepository.findById(id).get();
        Set<User> set = classObj.getStudents();
        set.remove(user);
        classRepository.save(classObj);
        return set;
    }
}
