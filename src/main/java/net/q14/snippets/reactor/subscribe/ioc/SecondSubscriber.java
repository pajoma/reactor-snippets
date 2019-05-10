package net.q14.snippets.reactor.subscribe.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
@Component
public class SecondSubscriber {

    @Autowired
    @Qualifier("numberEmitter")
    private Flux<String> emitter;


    @PostConstruct
    public void subscribe() {
        emitter.subscribe(val -> System.out.println("Second: "+val));
    }

}
