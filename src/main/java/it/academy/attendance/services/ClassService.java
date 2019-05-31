package it.academy.attendance.services;

import java.util.List;
import it.academy.attendance.models.Class;

public interface ClassService {
    List<Class> getAll();
    Class getById(Long id);
    boolean deleteById(Long id);
    Class save(Class item);
}
