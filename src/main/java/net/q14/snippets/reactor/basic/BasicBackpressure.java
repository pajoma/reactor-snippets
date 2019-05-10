package net.q14.snippets.reactor.basic;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class BasicBackpressure {

    // TODO Create a StepVerifier that initially requests all values
    // and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        Flux<User> log = flux.log();
        return StepVerifier.create(flux)
                .thenRequest(Long.MAX_VALUE)
                .expectNextCount(4)
                .expectComplete();
    }


    // TODO Create a StepVerifier that initially requests 1 value
    // and expects User.SKYLER then requests another value and
    // expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier

                .create(flux)
                .thenRequest(1)
                .expectNext(new User("hans", "dieter", "fr"))
                .thenCancel();


    }




}
