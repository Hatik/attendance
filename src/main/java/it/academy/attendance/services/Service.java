package it.academy.attendance.services;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    T getById(Long id);
    boolean deleteById(Long id);
    T save(T item);
}
