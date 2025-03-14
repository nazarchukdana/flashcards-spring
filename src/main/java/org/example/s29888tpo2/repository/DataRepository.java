package org.example.s29888tpo2.repository;

import java.util.List;

public interface DataRepository<T> {
    void add(T object);
    List<T> getAll();
}
