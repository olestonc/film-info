package com.example.demo.service.generics;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<T, K> implements GenericService<T, K> {

    JpaRepository<T, K> dao;

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> getById(K id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public void delete(K id) {
        dao.deleteById(id);

    }

    @Override
    @Transactional
    public T save(T entity) {
        return dao.save(entity);
    }

}
