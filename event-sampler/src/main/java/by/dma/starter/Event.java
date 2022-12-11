package by.dma.starter;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
class Event {

    private String type;
    private String payload;

}