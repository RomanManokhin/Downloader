package ru.rmanokhin.spring.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.rmanokhin.spring.initiator.Initialization;

@Component
@Profile("!test")
public class StarterImpl implements Starter{

    private  final Initialization initialization;

    @Autowired
    public StarterImpl(Initialization initialization) {
        this.initialization = initialization;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void EventListener() {
        initialization.start();
    }

}
