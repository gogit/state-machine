package com.thinktag.persist.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class StateTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currentState;
    private String nextState;

    public StateTransition() {
    }

    public StateTransition(String currentState, String nextState) {
        this.currentState = currentState;
        this.nextState = nextState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}