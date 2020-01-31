package com.thinktag.persist.service;

import com.thinktag.persist.model.EntityState;
import com.thinktag.persist.model.EventState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class Runtimeloop {

    @Autowired
    PersistenceService<EntityState> entityStatePersistenceService;

    @Autowired
    RedisPublisher redisPublisher;


    @Scheduled(fixedRate = 30000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 10000);

        try {
            Collection<Long> entities = entityStatePersistenceService.findAllEntitiesToProcess();
            if (entities != null) {
                for (Long entityId : entities) {

                    List<EntityState> states = entityStatePersistenceService.findLatestEntityState(entityId);
                    if (states != null && states.size() >= 1) {

                        EntityState es = states.get(0);
                        redisPublisher.publish(
                                new EventState(es.getStateMachine().getName(),
                                        es.getCurrentState(),
                                        es.getEntityType(),
                                        es.getEntityId(),
                                        new Date(es.getStartTime().getTime())));

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
