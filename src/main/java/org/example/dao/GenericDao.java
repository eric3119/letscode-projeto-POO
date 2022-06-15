package org.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.models.Model;

public abstract class GenericDao<T extends Model> {

    private static int lastID = 0;
    
    private List<T> entities = new ArrayList<>();

    public List<T> getAll() {
        return this.entities;
    }

    public T create(T entity) {
        entity.setId(++lastID);
        entities.add(entity);
        return entity;
    }

    public T getById(int id) {
        return entities.stream()
            .filter(obj -> obj.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public T update(T entity) {
        this.entities = entities.stream()
            .filter(obj -> obj.getId() == entity.getId()).collect(Collectors.toList());
        this.entities.add(entity);
        
        return entity;
    }
}
