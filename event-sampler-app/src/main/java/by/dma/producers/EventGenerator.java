package by.dma.producers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import by.dma.starter.EventPublisher;
import by.dma.starter.Event;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@Component
@ConditionalOnProperty(value = "eventsampler.enabled", havingValue = "true")
public class EventGenerator {

    private final EventPublisher eventPublisher;
    private static AtomicLong eventId = new AtomicLong();

    public EventGenerator(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 2000)
    void generateEvent() {
        var events = "humble,hungry,smart,weird,strange".split(",");
        var random = new Random();
        var eventType = events[random.nextInt(events.length)];
        Event event = Event.builder()
                .type(eventType)
                .payload(String.format("%s N%08d", eventType, eventId.incrementAndGet()))
                .build();
        eventPublisher.publishEvent(event);
    }
}
