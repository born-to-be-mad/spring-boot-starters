package by.dma;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import by.dma.starter.EventPublisher;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@SpringBootTest
class EventSamplerApplicationTest {
    @Autowired(required = false)
    private EventPublisher eventPublisher;

    @Test
    public void eventPublisherIsAvailable() {
        assertThat(eventPublisher).isNotNull();
    }
}