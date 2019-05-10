package net.q14.snippets.reactor.subscribe.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.time.Duration;

/*
    Snippet implementing the pub/sub pattern. We have a unknown number of subscribers (in this case 2)

 */
@SpringBootApplication
public class Emitter {
    public static void main(String[] args) {
        SpringApplication.run(Emitter.class, args);
    }

    @Bean("numberEmitter")
    public Flux<String> create() {
        return Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(20)
                .flatMap(lng -> {
                    return Mono.just("Number " + lng);
                });
    }
}
