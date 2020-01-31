package com.thinktag.persist.model;

import java.util.Date;

public class EventState {

    private String stateMachineName;

    private String name;

    private String entityType;

    private Long entityId;

    private Date date;

    public EventState(){}

    public EventState(String stateMachineName, String name, String entityType, long entityId){
        this.stateMachineName = stateMachineName;
        this.name = name;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public EventState(String stateMachineName, String name, String entityType, long entityId, Date date){
        this.stateMachineName = stateMachineName;
        this.name = name;
        this.entityType = entityType;
        this.entityId = entityId;
        this.date = date;
    }


    public String getStateMachineName() {
        return stateMachineName;
    }

    public void setStateMachineName(String stateMachineName) {
        this.stateMachineName = stateMachineName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
