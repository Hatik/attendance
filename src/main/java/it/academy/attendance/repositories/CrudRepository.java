package it.academy.attendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository<T> extends JpaRepository<T, Long> {
}
