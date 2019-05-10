package net.q14.snippets.reactor.basic;

import java.util.*;
import java.util.function.*;
import java.time.*;

import reactor.core.publisher.Flux;

/**
 *
 * See https://www.codingame.com/playgrounds/929/reactive-programming-with-reactor-3/Flux
 */
public class BasicFlux {


    static Flux<String> emptyFlux() {
        return Flux.empty();
    }


    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    public static Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }


    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    static Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }


    // TODO Create a Flux that emits an IllegalStateException
    static Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }


    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    public static Flux<Long> counter() {
        Flux f = Flux
                .interval(Duration.ofMillis(100))
                .take(10);

        f.subscribe(System.out::println, e -> {  }, () -> { });
        return f;

    }

}
