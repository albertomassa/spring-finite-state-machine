package com.am.fsm.builder;

import com.am.fsm.event.Events;
import com.am.fsm.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class FSMBuilder {

    @Autowired
    public Action<States, Events> firstAction;
    @Autowired
    public Action<States, Events> secondAction;
    @Autowired
    public Action<States, Events> thirdAction;

    @PostConstruct
    @Bean
    public StateMachine<States, Events> fsm() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(States.FIRST_STATE)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()

                .withExternal()
                .source(States.FIRST_STATE)
                .target(States.SECOND_STATE)
                .event(Events.FIRST_EVENT)
                .action(firstAction)

                .and()

                .withExternal()
                .source(States.SECOND_STATE)
                .target(States.THIRD_STATE)
                .event(Events.SECOND_EVENT)
                .action(secondAction)

                .and()

                .withExternal()
                .source(States.THIRD_STATE)
                .target(States.FIRST_STATE)
                .event(Events.THIRD_EVENT)
                .action(thirdAction)
        ;

        return builder.build();
    }



}
