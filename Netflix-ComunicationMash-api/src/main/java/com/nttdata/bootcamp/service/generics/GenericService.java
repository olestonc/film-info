package com.nttdata.bootcamp.service.generics;

import java.util.List;
import java.util.Optional;

/**
 * GenericService<T,K>
 * 
 * @param T --> Entity type
 * 
 * @param K --> Key type
 * 
 */
public interface GenericService<T, K> {
    List<T> getAll();

    Optional<T> getById(K id);

    void delete(K id);

    // Actualiza si existe un elemento con la Id de entity, sino crea un objeto
    // nuevo
    T save(T entity);

}
