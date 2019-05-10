package net.q14.snippets.reactor.subscribe.ioc.monos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.Disposable;
import reactor.core.publisher.*;
import reactor.util.context.Context;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.function.LongConsumer;

/*
    Snippet implementing the pub/sub pattern. We have a unknown number of subscribers (in this case 2)

    In this case, we have monos (simulation for repository access after web request),
    which we want to publish to an observabe flux


 */
@SpringBootApplication
public class Emitter {
    FluxSink<String> events;


    public static void main(String[] args) {
        SpringApplication.run(Emitter.class, args);
    }

    @PostConstruct
    public void create() {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .flatMap(lng -> this.simulateRequest(lng))
                .subscribe();
    }

    /*
        Creates the flux we are subscribing to.
     */
    @Bean("numberEmitter")
    public Flux<String> createObservable() {
        return Flux.create((FluxSink<String> sink) -> {
            this.events = sink;
        }).share();
    }


    /*
        Typical Resource Controller method, accessing for example a reactive data source
     */
    public Mono<String> simulateRequest(@RequestParam Long id) {
        //e.g. return this.repository.findById
        return Mono.just(String.format("This is response for request id: %d ", id))
                .publish(this::publishEvent);


        // push to the sink used by the flux with observers
/*        res.subscribe(str -> {
            if (this.events != null) this.events.next(str);
        });
        return res;*/
    }

    private Mono<String> publishEvent(Mono<String> stringMono) {

        return stringMono.flatMap(val -> {
            this.events.next(val);
            return Mono.just(val);
       });
    }
}
