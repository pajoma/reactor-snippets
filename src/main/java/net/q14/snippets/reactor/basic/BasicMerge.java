package net.q14.snippets.reactor.basic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BasicMerge {

    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return Flux.merge(flux1, flux2);
    }


    // flux1 is delayed, but we want to preserve order
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        // return Flux.mergeSequential(flux1, flux2);

        return Flux.concat(flux1, flux2);
    }

    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.concat(mono1, mono2);
    }
}
