package it.academy.attendance.repositories;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Set<Lecture> findFirstByClassId(Class classId);
}
