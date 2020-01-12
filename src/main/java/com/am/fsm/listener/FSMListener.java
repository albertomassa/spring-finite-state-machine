package com.am.fsm.listener;

import com.am.fsm.event.Events;
import com.am.fsm.state.States;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Log

@Component
public class FSMListener extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        log.info("fsm state changed from: " + from.getId() + " to: " + to.getId());
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
        log.info("fsm transition ended");
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
        log.info("fsm event: " + event.getPayload().name() + " not accepted");
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
        log.info("fsm state machine error");
    }


}
