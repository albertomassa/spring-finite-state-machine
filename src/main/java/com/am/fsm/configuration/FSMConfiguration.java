package com.am.fsm.configuration;

import com.am.fsm.event.Events;
import com.am.fsm.listener.FSMListener;
import com.am.fsm.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class FSMConfiguration {

    @Autowired
    public StateMachine<States, Events> fsm;

    @Bean
    public FSMListener stateMachineEventListener() {
        FSMListener listener = new FSMListener();
        fsm.addStateListener(listener);
        return listener;
    }

}
