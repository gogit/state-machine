package com.thinktag.persist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinktag.persist.model.EntityState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersistenceService<T> {

    @Autowired
    PersistenceRepository repo;

    public Collection<Long> findAllEntitiesToProcess(){
        return repo.findAllEntitiesToProcess();
    }

    public List<EntityState> findLatestEntityState(Long entityId) {
        return new ArrayList<>(repo.findLatestEntityState(entityId));
    }

    @Transactional
    public Collection<T> findAll(String type) {
        return repo.findAll(type);
    }

    @Transactional
    public T find(String id, Class<T> type) {
        return repo.find(type, Long.parseLong(id));
    }

    @Transactional
    public T findByQuery(Class<T> type, String query) {
        return repo.find(type, query);
    }


    @Transactional
    public void save(Class<T> type, T object) {
        repo.save(type, object);
    }

    @Transactional
    public void saveAll(Class<T> type, List<T> objects) {
        for(T object: objects) {
            repo.save(type, object);
        }
    }

    @Transactional
    public <T> void update(String id, Class<T> type, T ob) {
        repo.update(type, ob);
    }


}
