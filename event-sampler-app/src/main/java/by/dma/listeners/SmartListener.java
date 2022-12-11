package by.dma.listeners;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import by.dma.starter.Event;
import by.dma.starter.EventListener;
import by.dma.starter.EventListenerProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "eventsampler.enabled", havingValue = "true")
public class SmartListener extends EventListener {

    public SmartListener(EventListenerProperties properties) {
        super(properties);
    }

    @Override
    public String getSubscribedEventType() {
        return "smart";
    }

    @Override
    public void onEvent(Event event) {
        log.info("# Event consumed:{}", event);
    }
}
