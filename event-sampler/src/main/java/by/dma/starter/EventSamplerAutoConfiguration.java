package by.dma.starter;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple entry point to the features of the starter, a @Configuration class is required.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@Configuration
// It’s always a good idea to allow the features of a Spring Boot starter to be disabled
// ConditionalOnProperty we tell Spring to only include the EventAutoConfiguration (and all the beans it declares)
// into the application context if the property 'eventsampler.enabled' is set to true.
@ConditionalOnProperty(value = "eventsampler.enabled", havingValue = "true")
// The @ConditionalOnClass annotation tells Spring to only activate the auto-configuration
// when the class by.dma.connector.KafkaConnector is on the classpath
@ConditionalOnClass(name = "by.dma.connector.KafkaConnector")
@EnableConfigurationProperties(EventListenerProperties.class)
class EventSamplerAutoConfiguration {
    @Bean
    EventPublisher eventPublisher(List<EventListener> listeners){
        return new EventPublisher(listeners);
    }
}
