package net.q14.snippets.reactor.subscribe.simple;

import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class SimpleEmitter {

    public static void main(String[] args) {


        Disposable subscribe = new SimpleEmitter()
                .create()
                .log()
                .subscribe(val -> System.out.println(val));


        while(! subscribe.isDisposed()) {
            // wait
        }

    }

    /*
        Creates a simple series of 50 strings
     */
    public Flux<String> create() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(50)
                .flatMap(lng -> {
                    return Mono.just("Number " + lng);
                });
    }
}
