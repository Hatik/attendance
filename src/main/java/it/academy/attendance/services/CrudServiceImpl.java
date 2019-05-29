package it.academy.attendance.services;

import it.academy.attendance.repositories.CrudRepository;
import it.academy.attendance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CrudServiceImpl<T> implements Service<T> {
    @Autowired
    private CrudRepository<T> crudRepository;

    @Override
    public List<T> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public T getById(Long id) {
        return crudRepository.findById(id).get();
    }

    @Override
    public T save(T item) {
        return crudRepository.save(item);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            crudRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            // TODO write to log object
            return false;
        }
    }
}
