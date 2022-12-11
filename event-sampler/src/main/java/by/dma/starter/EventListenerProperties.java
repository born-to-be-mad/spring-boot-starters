package by.dma.starter;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "eventsampler.listener")
@Data
public class EventListenerProperties {

    /**
     * List of event types that will be passed to {@link EventListener} implementations.
     * All other events will be ignored.
     */
    private List<String> enabledEvents = Collections.emptyList();

}