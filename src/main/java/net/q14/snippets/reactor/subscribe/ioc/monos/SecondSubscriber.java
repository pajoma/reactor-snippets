package net.q14.snippets.reactor.subscribe.ioc.monos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SecondSubscriber {

    @Autowired
    @Qualifier("numberEmitter")
    private Flux<String> emitter;


    private Disposable subscription;


    @PostConstruct
    public void subscribe() {
        subscription = Flux.from(emitter).subscribe(val -> System.out.println("Second: " + val));
        System.out.println("Second is subscribed");
    }

    @PreDestroy
    public void unsubscribe() {
        this.subscription.dispose();
    }

}
