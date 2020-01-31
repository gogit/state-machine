package com.thinktag.persist;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinktag.persist.model.EventState;
import com.thinktag.persist.model.StateMachine;
import com.thinktag.persist.model.StateTransition;
import org.junit.Test;

import java.util.Arrays;

public class GenerateJsonTest {

    @Test
    public void generateStateTransition() throws Exception {
        StateTransition st1 = new StateTransition("Register", "Email-Sent");
        StateTransition st2 = new StateTransition("Email-Sent", "Email-Validated");
        StateTransition st3 = new StateTransition("Email-Sent", "Email-Invalid");
        StateTransition st4 = new StateTransition("Email-Validated", "Address-Verified");
        StateTransition st5 = new StateTransition("Email-Validated", "Address-Invalid");
        StateTransition st6 = new StateTransition("Address-Verified", "Customer-Setup");


        StateMachine sm = new StateMachine("user-onboarding-v1", Arrays.asList(st1, st2, st3, st4, st5, st6));

        System.out.println(writeJson(sm));
    }

    @Test
    public void generateEventState() throws Exception {

        EventState state = new EventState("user-onboarding-v1","Register","User",1);
        System.out.println(writeJson(state));

    }

    public String writeJson(Object ob) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(ob);
    }
}
