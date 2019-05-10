package net.q14.snippets.reactor.subscribe;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

/*
    Inspired by vanseverk/flux-share-demo.

    One Flux, many subscribers
 */
public class FluxSharing {
    Flux<Long> start;
    int max = 10;

    public FluxSharing() {
        this.share();
        this.subscribe(this.start, "First: ");
        this.subscribe(this.start, "Second: ");
        this.subscribe(this.start, "Third: ");


        while (!start.subscribe().isDisposed()) {
            // wait
        }

    }

    public static void main(String[] args) {
        new FluxSharing();
    }

    private void subscribe(Flux<Long> start, String prefix) {
        AtomicBoolean dispose = new AtomicBoolean(false);
        Disposable localSub = Flux.from(start)

                // cleaning up (remember to return a mono
                .flatMap(out -> {if(out >= this.max) dispose.set(true); return Mono.just(out);})

                // transform to string
                .flatMap(out -> Mono.just(String.format("%s Number %d", prefix, out)))

                // print on event
                .subscribe(out ->
                        System.out.println(out)
                );

        // cleaning up
        if(dispose.get()) localSub.dispose();

    }


    private void share() {
        start = Flux.interval(Duration.ofMillis(100)).share().take(max);
        start.then();
    }


}
