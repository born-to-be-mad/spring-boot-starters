package by.dma.listeners;

import by.dma.starter.Event;
import by.dma.starter.EventListener;
import by.dma.starter.EventListenerProperties;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "eventsampler.enabled", havingValue = "true")
public class HumbleListener extends EventListener {

    public HumbleListener(EventListenerProperties properties) {
        super(properties);
    }

    @Override
    public String getSubscribedEventType() {
        return "humble";
    }

    @Override
    public void onEvent(Event event) {
        log.info("# Event consumed:{}", event);
    }
}
