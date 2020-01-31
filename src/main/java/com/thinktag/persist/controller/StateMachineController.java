package com.thinktag.persist.controller;

import com.thinktag.persist.model.StateMachine;
import com.thinktag.persist.model.StateTransition;
import com.thinktag.persist.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class StateMachineController {

    @Autowired
    PersistenceService<StateMachine> persistenceService;

    @PostMapping("api/stateMachine")
    @ResponseBody
    void save(@RequestBody StateMachine stateMachine) {
        persistenceService.save(StateMachine.class, stateMachine);
    }

    @GetMapping("api/stateMachine")
    @ResponseBody
    Collection<StateMachine> get() {
        return persistenceService.findAll(StateMachine.class.getName());
    }

}