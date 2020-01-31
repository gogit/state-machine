package com.thinktag.persist.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StateMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<StateTransition> stateTransitions=new ArrayList<>();

    public StateMachine(){

    }

    public StateMachine(String name, List<StateTransition> transitions){
        this.name = name;
        stateTransitions.addAll(transitions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StateTransition> getStateTransitions() {
        return stateTransitions;
    }

    public void setStateTransitions(List<StateTransition> stateTransitions) {
        this.stateTransitions = stateTransitions;
    }

    public boolean isValidState(String state){
        for(StateTransition sm: stateTransitions){
            if(sm.getCurrentState().equals(state)||sm.getNextState().equals(state)){
                return true;
            }
        }
        return false;
    }

    public boolean isFinalState(String state){
        for(StateTransition sm: stateTransitions){
            if(sm.getCurrentState().equals(state)){
                return false;
            }
        }
        return true;
    }
}
