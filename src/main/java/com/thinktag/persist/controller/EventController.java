package com.thinktag.persist.controller;

import com.thinktag.persist.model.EntityState;
import com.thinktag.persist.model.EventState;
import com.thinktag.persist.model.StateMachine;
import com.thinktag.persist.service.PersistenceService;
import com.thinktag.persist.service.RedisPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    PersistenceService<EntityState> entityStatePersistenceService;

    @Autowired
    PersistenceService<StateMachine> stateMachinePersistenceService;

    @PostMapping("api/stateMachineEvent")
    @ResponseBody
    void save(@RequestBody EventState eventState)throws Exception {
        StateMachine sm=  doChecksReturnStateMachine(eventState);
        EntityState es = new EntityState();
        es.setCurrentState(eventState.getName());
        es.setEntityId(eventState.getEntityId());
        es.setEntityType(eventState.getEntityType());
        es.setFinalState(sm.isFinalState(eventState.getName()));
        es.setStateMachine(sm);
        es.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

        entityStatePersistenceService.save(EntityState.class, es);
    }

    @GetMapping("api/stateMachineEvent")
    @ResponseBody
    Collection<EntityState> get()throws Exception {
        return entityStatePersistenceService.findAll(EntityState.class.getName());
    }


    private StateMachine doChecksReturnStateMachine(EventState eventState){
        Collection<StateMachine> l =stateMachinePersistenceService.findAll(StateMachine.class.getName());
        StateMachine sma=null;
        for(StateMachine sm: l){
            if(sm.getName().equals(eventState.getStateMachineName())){
                sma = sm;
                break;
            }
        }
        if(sma==null){
            throw new IllegalArgumentException("State Machine not found");
        }
        if(!sma.isValidState(eventState.getName())){
            throw new IllegalArgumentException("Invalid state "+eventState.getName());
        }
        return sma;
    }

}
