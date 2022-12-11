package by.dma.starter;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@Builder
public class Event {

    private String type;
    private String payload;

}