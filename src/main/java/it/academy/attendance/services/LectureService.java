package it.academy.attendance.services;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.Lecture;

import java.util.List;
import java.util.Set;

public interface LectureService {
    List<Lecture> getAll();
    Lecture getById(Long id);
    boolean deleteById(Long id);
    Lecture save(Lecture item);

    Set<Lecture> getLecturesByClass(Long classId);
    Lecture save(Lecture item, Long classId);
}
