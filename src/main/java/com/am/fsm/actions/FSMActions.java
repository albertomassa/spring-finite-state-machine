package com.am.fsm.actions;

import com.am.fsm.event.Events;
import com.am.fsm.state.States;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Log

@Component
public class FSMActions {

    @Autowired
    StateMachine<States, Events> fsm;

    @Bean
    public Action<States, Events> firstAction() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> context) {
                log.info("state: " + fsm.getState().getId());
                log.info("event: " + context.getEvent().name());
                log.info("action: first action executed!");
            }
        };
    }

    @Bean
    public Action<States, Events> secondAction() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> context) {
                log.info("state: " + fsm.getState().getId());
                log.info("event: " + context.getEvent().name());
                log.info("action: second action executed!");
            }
        };
    }

    @Bean
    public Action<States, Events> thirdAction() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> context) {
                log.info("state: " + fsm.getState().getId());
                log.info("event: " + context.getEvent().name());
                throw new RuntimeException("state_machine_error");
            }
        };
    }

}
