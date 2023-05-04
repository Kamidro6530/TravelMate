package com.example.services;

import com.example.model.User;

import java.util.Set;

public interface CrudService<T,ID> {

    T save(T object);

    T findById(ID id);

    Set<T> findAll();

    void deleteById(ID id);

    void delete(T object);
}
