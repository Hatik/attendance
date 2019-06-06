package it.academy.attendance.repositories;

import it.academy.attendance.models.Class;
import it.academy.attendance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> getAllByTeacher(User user);
}
