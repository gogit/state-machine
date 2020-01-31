package com.thinktag.persist.service;

import com.thinktag.persist.model.EntityState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;

@Repository
public class PersistenceRepository {

    @PersistenceContext
    EntityManager em;

    final String FIND_ALL_ENTITIES = "SELECT DISTINCT entityId FROM EntityState where finalState IS FALSE";

    final String FIND_ENTITY_STATE = "SELECT es FROM EntityState es WHERE entityId = ?1 ORDER BY id desc";

    public Collection<Long> findAllEntitiesToProcess(){
        Query query = em.createQuery(FIND_ALL_ENTITIES);
        return (Collection<Long>) query.getResultList();
    }

    public Collection<EntityState> findLatestEntityState(Long entityId){
        Query query = em.createQuery(FIND_ENTITY_STATE);
        query.setParameter(1, entityId);
        return (Collection<EntityState>) query.getResultList();
    }


    public <T> Collection<T> findAll(String type) {
        Query query = em.createQuery("SELECT e FROM "+type+" e");
        return (Collection<T>) query.getResultList();
    }

    public <T> T find(Class<T> clazz, Long id) {
        return em.find(clazz, id);
    }

    public <T> T find(Class<T> clazz, String queryString) {
        Query query = em.createQuery(queryString);
        return ((List<T>) query.getResultList()).get(0);
    }

    public <T> T save(Class<T> clazz, T t) {
        em.persist(t);
        return t;
    }

    public <T> void update(Class<T> clazz, T t) {
        em.merge(t);
    }
}
