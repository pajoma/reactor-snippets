package net.q14.snippets.reactor;

import net.q14.snippets.reactor.basic.BasicFlux;
import org.junit.Test;
import reactor.test.StepVerifier;
import static org.assertj.core.api.Assertions.*;

/*

 */
public class BasicTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testFooBar() {
        StepVerifier.create(BasicFlux.fooBarFluxFromValues())
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();
    }

    @Test
    public void testError() {
        StepVerifier.create(BasicFlux.fooBarFluxFromValues().log())
                .assertNext(val -> assertThat(val).isEqualTo("foo"))
                .expectNext("bar")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void counter() {
        /*
            StepVerifier.create(T<Publisher>).{expectations...}.verify()

        StepVerifier.withVirtualTime(() -> BasicFlux.counter())
                .thenAwait(Duration.ofSeconds(10))
                .expectNextMatches(l -> l >= 0)
                .expectNextCount(9)
                .verifyComplete(); // has to be called for subscription*/
        StepVerifier.create(BasicFlux.counter())
                .expectNextCount(10)
                .verifyComplete();

        /*
		StepVerifier.withVirtualTime(supplier)
		            .expectSubscription()
                    .thenAwait(Duration.ofSeconds(3600))
		            .expectNextCount(3600)
		            .expectComplete()
		            .verify();
	}

         */
    }

}
