package com.am.fsm;

import com.am.fsm.event.Events;
import com.am.fsm.state.States;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

@Log

@SpringBootTest
public class FSMTest {

    @Autowired
    StateMachine<States, Events> fsm;

    @Test
    public void testFsmLifeCycle() {
        fsm.start();
        Assertions.assertNotNull(fsm);
        Assertions.assertEquals(fsm.getInitialState().getId(), fsm.getState().getId());

        fsm.sendEvent(Events.FIRST_EVENT);
        Assertions.assertEquals(States.SECOND_STATE, fsm.getState().getId());

        fsm.sendEvent(Events.SECOND_EVENT);
        Assertions.assertEquals(States.THIRD_STATE, fsm.getState().getId());
    }

    @Test
    public void testFsmLifeCycleWithNotAcceptedEvent() {
        fsm.start();
        Assertions.assertNotNull(fsm);
        Assertions.assertEquals(fsm.getInitialState().getId(), fsm.getState().getId());

        fsm.sendEvent(Events.FIRST_EVENT);
        Assertions.assertEquals(States.SECOND_STATE, fsm.getState().getId());

        fsm.sendEvent(Events.FIRST_EVENT);
        Assertions.assertEquals(States.SECOND_STATE, fsm.getState().getId()); //no state-transition
    }

    @Test
    public void testFsmLifeCycleWithRuntimeException() {
        fsm.start();
        Assertions.assertNotNull(fsm);
        Assertions.assertEquals(fsm.getInitialState().getId(), fsm.getState().getId());

        fsm.sendEvent(Events.FIRST_EVENT);
        Assertions.assertEquals(States.SECOND_STATE, fsm.getState().getId());

        fsm.sendEvent(Events.SECOND_EVENT);
        Assertions.assertEquals(States.THIRD_STATE, fsm.getState().getId());

        fsm.sendEvent(Events.THIRD_EVENT); //transition with error
        Assertions.assertEquals(States.THIRD_STATE, fsm.getState().getId()); //no state-transition
    }

}
