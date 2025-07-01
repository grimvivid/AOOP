package com.mycompany.motorphgui2.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    Optional<T> get(ID id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
