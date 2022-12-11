package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
@SpringBootApplication
@EnableScheduling
public class EventSamplerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSamplerApplication.class, args);
    }

}
