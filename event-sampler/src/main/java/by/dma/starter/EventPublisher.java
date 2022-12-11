package by.dma.starter;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@Slf4j
@RequiredArgsConstructor
public class EventPublisher {

    /**
     * The listeners that should be notified about published events.
     */
    private final List<EventListener> listeners;

    public void publishEvent(Event event) {
        log.info("Publishing event: {}", event);
        for (EventListener listener : listeners) {
            listener.receive(event);
        }
    }

}
