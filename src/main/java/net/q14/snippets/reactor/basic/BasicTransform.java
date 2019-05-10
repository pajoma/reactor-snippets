package net.q14.snippets.reactor.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BasicTransform {

    Mono<User> capitalizeOne(Mono<User> mono) {
        /*
            Note: map is synchronous, use flatMap instead
         */

        return mono.map(u -> {
            u.setUsername(u.getUsername().toUpperCase());
            return u;
        });
    }

    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(u -> { return this.asyncCapitalizeUser(u); });
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }


    String capitalizeFirstLetter(String value) {
        return value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase());
    }


}
